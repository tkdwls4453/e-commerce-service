package com.hanghae.payment.application;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.payment.application.client.ItemClient;
import com.hanghae.payment.application.client.OrderClient;
import com.hanghae.payment.application.client.request.RestoreStockRequest;
import com.hanghae.payment.application.client.response.OrderWithSimpleOrderItemsResponse;
import com.hanghae.payment.application.client.response.SimpleOrderItemResponse;
import com.hanghae.payment.application.port.PaymentRepository;
import com.hanghae.payment.domain.Payment;
import com.hanghae.payment.domain.dto.response.PaymentDto;
import com.hanghae.payment.exception.NotFoundPaymentException;
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

/**
 * 결제 api
 * 1. userId 와 paymentId 를 입력 받는다.
    * 2. 가짜 결제 진행 (가짜 결제 서비스, 여기서 20% 실패를 뿌린다)
 * 3. 실패시, 재고 복구를 요청한다. (결제 상태를 실패로 변경)
    * 4. 성공시, 결제 상태를 결제완료로 번경
 *      - 주문 상태를 진행중으로 변경 요청
 *      - 주문 상태 배달중 변경 메시지를 딜레이큐에 담음 (24시간 후 진행)
 *
*  무엇을 반환할까? 성공 여부만 반환하면 될 듯
**/



@Transactional
@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderClient orderClient;
    private final ItemClient itemClient;
    private final RabbitMQSenderService rabbitMQSenderService;

    // 결제 시도, 결제 정보 생성 (결제 대기)
    public PaymentDto create(Long userId, Long orderId){

        // 주문 정보 조회 (주문 아이템들의 주문량 포함)
        OrderWithSimpleOrderItemsResponse data = orderClient.readOrder(orderId).getData();

        // 결제 정보 생성후 저장
        Payment payment = Payment.create(orderId, userId, data.totalPrice());
        Payment savedPayment = paymentRepository.save(payment);

        return PaymentDto.from(savedPayment);
    }

    // 결제
    public String processPayment(Long userId, Long paymentId, String resultCode){

        // 유저의 결제 정보가 맞는지 검증 로직 필요

        // 결제 정보를 조회한다.
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(
            NotFoundPaymentException::new
        );

        if(!resultCode.equals("200")){
            // 주문 아이디로 주문 아이템 정보들을 조회
            OrderWithSimpleOrderItemsResponse data = orderClient.readOrder(payment.getOrderId()).getData();

            // 주문 아이템의 주문량 정보들을 이용해 재고 복구 요청을 보낸다.
            List<RestoreStockRequest.Info> infos = new ArrayList<>();

            for(SimpleOrderItemResponse item : data.orderItems()) {
                infos.add(
                    RestoreStockRequest.Info.builder()
                        .itemId(item.itemId())
                        .quantity(item.quantity())
                        .build()
                );
            }

            // 재고 복구 요청을 보낸다. TODO: 실패 상황도 생각해 보아야 함.
            CustomResponse<String> response = itemClient.restoreStock(
                RestoreStockRequest.builder()
                    .infos(infos)
                    .build()
            );

            // 결제 상태를 결제 실패로 변경
            return "결제 실패";
        }

        // 주문 상태를 진행중으로 변경 요청 -> 주문 서비스에 TODO: 요청 실패시 고려해야 함
        orderClient.advanceOrderStatus(payment.getOrderId());

        // 결제 상태를 완료로 변경
        payment.complete();
        paymentRepository.update(payment);

        // 딜레이 큐에 주문 상태 변경 메시지를 발행 (준비중 -> 배달중, 24시간 후 실행)
        rabbitMQSenderService.sendDelayedMessage(payment.getOrderId().toString());
        return "결제 성공";
    }
}
