package com.hanghae.order.application;

import com.hanghae.order.application.client.response.ItemProductResponse;
import com.hanghae.order.application.port.OrderItemRepository;
import com.hanghae.order.application.port.OrderRepository;
import com.hanghae.order.domain.Order;
import com.hanghae.order.domain.OrderItem;
import com.hanghae.order.domain.dto.request.OrderCreateDto;
import com.hanghae.order.application.client.ItemClient;
import com.hanghae.order.domain.dto.response.OrderDto;
import com.hanghae.order.domain.dto.response.OrderItemDto;
import com.hanghae.order.domain.dto.response.SimpleOrderDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

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

    public OrderDto createOrder(Long userId, List<OrderCreateDto> orderCreateDtoList) {

        Map<Long, Integer> quantityMap = new HashMap<>();

        for(OrderCreateDto orderCreateDto : orderCreateDtoList) {
            quantityMap.put(orderCreateDto.itemId(), orderCreateDto.quantity());
        }
        // 아이템 아이디로 아이템 조회 (product service 에 feign client)
        List<Long> itemIds = orderCreateDtoList.stream().map(OrderCreateDto::itemId).toList();

        List<ItemProductResponse> itemAndProductInfo = itemClient.getItemProducts(itemIds).getData();
        System.out.println(itemAndProductInfo);

        List<OrderItem> orderItems = new ArrayList<>();

        // orderItem 리스트 내용으로 order 생성
        Order order = Order.create(userId);

        // 아이템 내용과 수량 내용으로 orderItem 생성
        for(int i=0; i<itemIds.size(); i++) {
            ItemProductResponse info = itemAndProductInfo.get(i);
            OrderCreateDto orderCreateDto = orderCreateDtoList.get(i);
            OrderItem orderItem = OrderItem.create(orderCreateDto.quantity(), info.price(), info.itemId());
            orderItems.add(orderItem);
        }

        order.addOrderItem(orderItems);
        List<OrderItemDto> orderItemDtos = itemAndProductInfo.stream().map(response -> OrderItemDto.from(response, quantityMap)).toList();

        // order 와 orderItem 을 데이터베이스에 저장
        Order savedOrder = orderRepository.save(order);
        return OrderDto.from(savedOrder, orderItemDtos);
    }

    @Transactional(readOnly = true)
    public List<SimpleOrderDto> getOrders(Integer userId) {
        List<Order> orders = orderRepository.findByUserIdAndStatusIsNotPending(userId);

        return orders.stream().map(SimpleOrderDto::from).toList();
    }

    @Transactional(readOnly = true)
    public SimpleOrderDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId);

        return SimpleOrderDto.from(order);
    }
}
