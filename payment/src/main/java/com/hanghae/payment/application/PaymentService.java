package com.hanghae.payment.application;

import com.hanghae.payment.application.client.ItemClient;
import com.hanghae.payment.application.client.OrderClient;
import com.hanghae.payment.application.client.request.ReduceStockRequest;
import com.hanghae.payment.application.client.request.ReduceStockRequest.Info;
import com.hanghae.payment.application.client.response.OrderWithSimpleOrderItemsResponse;
import com.hanghae.payment.application.client.response.SimpleOrderItemResponse;
import com.hanghae.payment.application.port.PaymentRepository;
import com.hanghae.payment.domain.Payment;
import com.hanghae.payment.domain.dto.response.PaymentDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *  * 결제 시도 api
 *  * 1. userId, orderId 를 입력 받는다. -> controller
 *  * 2. 주문 정보를 조회한다 -> 주문 서비스에 요청
 *  * 3. 주문 정보 바탕으로 결제 정보를 생성한다. (상태는 결제중) -> domain
 *  * 4. 결제 정보를 데이터베이스에 저장한다. -> repository
 *  * 5. 상품 서비스에게 재고 감소를 요청한다. -> service
 */
@Transactional
@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderClient orderClient;
    private final ItemClient itemClient;

    public PaymentDto create(Long orderId){
        OrderWithSimpleOrderItemsResponse data = orderClient.readOrder(orderId).getData();
        System.out.println(data.orderItems());

        List<Info> infos = new ArrayList<>();

        for(SimpleOrderItemResponse item : data.orderItems()) {
            infos.add(
                Info.builder()
                    .itemId(item.itemId())
                    .quantity(item.quantity())
                    .build()
            );
        }

        itemClient.reduceStock(
            ReduceStockRequest.builder()
                .infos(infos)
            .build()
        );

        Payment payment = Payment.create(orderId, data.totalPrice());
        Payment savedPayment = paymentRepository.save(payment);

        return PaymentDto.from(savedPayment);
    }
}
