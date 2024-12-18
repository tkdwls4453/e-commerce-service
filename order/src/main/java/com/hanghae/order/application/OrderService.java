package com.hanghae.order.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghae.common.annotation.ServiceLogExecutionTime;
import com.hanghae.order.application.client.response.ItemProductResponse;
import com.hanghae.order.application.port.OrderItemRepository;
import com.hanghae.order.application.port.OrderRepository;
import com.hanghae.order.domain.Order;
import com.hanghae.order.domain.OrderItem;
import com.hanghae.order.domain.dto.request.OrderCreateDto;
import com.hanghae.order.application.client.ItemClient;
import com.hanghae.order.domain.dto.response.OrderDto;
import com.hanghae.order.domain.dto.response.OrderItemDto;
import com.hanghae.order.domain.dto.response.OrderWithSimpleOrderItemsDto;
import com.hanghae.order.domain.dto.response.SimpleOrderDto;
import com.hanghae.order.domain.dto.response.SimpleOrderItemDto;
import com.hanghae.order.exception.InsufficientStockException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 아이템 - 수량 리스트와 유저 아이디를 입력받는다. -> controller
 * 입력 받은 정보로 주문을 생성한다. -> domain
 *  유효성 검사 : 주문 아이템의 재고가 주문 수량보다 많은지 확인, 주문 수량이 0이하인지 체크
 *  주문시 주문 상태를 준비중으로 변경
 *  딜레이 큐를 이용해서 24시간 이후에 주문 상태를 배송중으로 변경 (RabbitMQ)
 *  그 후 24시간 후에 배송중 -> 배송 완료로 변경
 * 주문을 데이터베이스에 저장한다.
 */

@Transactional
@RequiredArgsConstructor
@Service
public class OrderService {

    private final ItemClient itemClient;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final RedisScript<String> stockDecreaseScript;
    private final ObjectMapper objectMapper;

    @ServiceLogExecutionTime
    public OrderDto createOrder(Long userId, List<OrderCreateDto> orderCreateDtoList)
        throws IOException {

        // 레디스에서 재고 감소
        reduceStock(orderCreateDtoList);

        // 레디스에서 아이템 정보 조회 (없으면 상품 서비스에게 요청)
        List<ItemProductResponse> itemProductResponseList = getItemProductResponses(orderCreateDtoList);

        List<OrderItem> orderItems = new ArrayList<>();

        // orderItem 리스트 내용으로 order 생성
        Order order = Order.create(userId);


        // 아이템 내용과 수량 내용으로 orderItem 생성
        for(int i=0; i<itemProductResponseList.size(); i++) {
            ItemProductResponse realItemInfo = itemProductResponseList.get(i);
            OrderCreateDto orderItemInfo = orderCreateDtoList.get(i);

            OrderItem orderItem = OrderItem.create(orderItemInfo.quantity(), realItemInfo.price(), realItemInfo.itemId(), order);
            orderItems.add(orderItem);
        }

        order.addOrderItem(orderItems);
        List<OrderItemDto> orderItemDtos = itemProductResponseList.stream().map(response -> OrderItemDto.from(response, orderCreateDtoList)).toList();

        // order 와 orderItem 을 데이터베이스에 저장
        Order savedOrder = orderRepository.save(order);
        return OrderDto.from(savedOrder, orderItemDtos);
    }

    private List<ItemProductResponse> getItemProductResponses(List<OrderCreateDto> orderCreateDtoList) throws JsonProcessingException {
        List<ItemProductResponse> itemProductResponseList = new ArrayList<>();

        for(OrderCreateDto info : orderCreateDtoList){
            String itemKey = "item" + info.itemId() + ":info";
            String itemJson = redisTemplate.opsForValue().get(itemKey);

            ItemProductResponse itemProductResponse = null;
            if(itemJson == null){
                // Feign Client 로 직접 조회 후 redis 에 저장
                itemProductResponse = itemClient.getItemProduct(info.itemId()).getData();

                String key = "item" + info.itemId() + ":info";
                try {
                    String data = objectMapper.writeValueAsString(itemProductResponse);
                    redisTemplate.opsForValue().set(key, data);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("JSON 직렬화 실패", e);
                }

            }else{
                itemProductResponse = objectMapper.readValue(itemJson, ItemProductResponse.class);
            }

            itemProductResponseList.add(itemProductResponse);
        }
        return itemProductResponseList;
    }

    private void reduceStock(List<OrderCreateDto> orderCreateDtoList) throws IOException {

        // Redis KEYS (아이템별 재고 키 리스트)
        List<String> keys = orderCreateDtoList.stream()
            .map(info -> "item" + info.itemId() + ":stock")
            .toList();

        // Redis ARGV (아이템별 감소할 수량 리스트)
        List<String> args = orderCreateDtoList.stream()
            .map(info -> String.valueOf(info.quantity()))
            .toList();

        // Lua 스크립트 실행 및 결과 확인
        String result = redisTemplate.execute(stockDecreaseScript, keys, args.toArray());

        // 결과가 에러 메시지인 경우 처리
        if (result != null && result.contains("err")) {
            throw new RuntimeException("Redis error: " + result);
        }

        if (result == null || !result.equals("All items updated successfully")) {
            throw new InsufficientStockException();
        }
    }

    @Transactional(readOnly = true)
    public List<SimpleOrderDto> getOrders(Long userId) {
        List<Order> orders = orderRepository.findByUserIdAndStatusIsNotPending(userId);

        return orders.stream().map(SimpleOrderDto::from).toList();
    }

    @Transactional(readOnly = true)
    public OrderWithSimpleOrderItemsDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId);
        List<OrderItem> orderItems = orderItemRepository.findAllByOrder(order);
        List<SimpleOrderItemDto> orderItemDtos = orderItems.stream().map(SimpleOrderItemDto::from).toList();

        return OrderWithSimpleOrderItemsDto.create(order, orderItemDtos);
    }

    public void advanceOrderStatus(Long orderId) {
        Order order = orderRepository.findById(orderId);
        order.advanceStatus();

        orderRepository.update(order);
    }
}
