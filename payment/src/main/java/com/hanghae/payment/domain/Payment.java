package com.hanghae.payment.domain;

import com.hanghae.payment.exception.InvalidPriceException;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

/**
 * 결제 시도 api
 * 1. userId, orderId 를 입력 받는다. -> controller
 * 2. 결제 정보를 생성한다. (상태는 결제중) -> domain
 * 3. 결제 정보를 데이터베이스에 저장한다. -> repository
 * 4. 상품 서비스에게 재고 감소를 요청한다. -> service
 *
 * 결제 시도는 했지만 실제 결제 api 를 호출하지 않는 경우? -> 고객 이탈로 간주 재고 복구 (1분 대기)
 *
 * 결제 api
 * 1. userId 와 paymentId 를 입력 받는다.
 * 2. 가짜 결제 진행 (가짜 결제 서비스, 여기서 20% 실패를 뿌린다)
 * 3. 실패시, 재고 복구를 요청한다. (결제 상태를 실패로 변경)
 * 4. 성공시, 결제 상태를 결제완료로 번경
 *      - 주문 상태를 진행중으로 변경 요청
 *      - 주문 상태 배달중 변경 메시지를 딜레이큐에 담음 (24시간 후 진행)
 */

@Getter
public class Payment {
    private final Long id;
    private final Long orderId;
    private final Long userId;
    private final Integer amount;
    private PaymentStatus paymentStatus;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder
    private Payment(Long id, Long orderId, Long userId, Integer amount, PaymentStatus paymentStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {

        if(amount < 0 ){
            throw new InvalidPriceException();
        }

        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Payment create(Long orderId,Long userId, Integer amount){
        return Payment.builder()
            .orderId(orderId)
            .userId(userId)
            .amount(amount)
            .paymentStatus(PaymentStatus.PAYMENT_PENDING)
            .build();
    }

    public void complete() {
        this.paymentStatus = PaymentStatus.PAYMENT_COMPLETED;
    }
}

