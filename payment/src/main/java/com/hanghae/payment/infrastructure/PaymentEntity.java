package com.hanghae.payment.infrastructure;

import com.hanghae.common.infrastructure.BaseEntity;
import com.hanghae.payment.domain.Payment;
import com.hanghae.payment.domain.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "payments")
public class PaymentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Integer amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Builder
    private PaymentEntity(Long id, Long orderId, Long userId, Integer amount, PaymentStatus paymentStatus) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    public static PaymentEntity from(Payment payment) {
        return PaymentEntity.builder()
            .orderId(payment.getOrderId())
            .userId(payment.getUserId())
            .amount(payment.getAmount())
            .paymentStatus(payment.getPaymentStatus())
            .build();
    }

    public Payment toDomain() {
        return Payment.builder()
            .id(this.id)
            .amount(this.amount)
            .userId(this.userId)
            .orderId(this.orderId)
            .paymentStatus(this.paymentStatus)
            .createdAt(this.getCreatedAt())
            .updatedAt(this.getUpdatedAt())
            .build();
    }

    public void updateByDomain(Payment payment) {
        this.paymentStatus = payment.getPaymentStatus();
    }
}
