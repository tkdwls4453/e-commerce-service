package com.hanghae.payment.infrastructure.repository;

import com.hanghae.payment.application.port.PaymentRepository;
import com.hanghae.payment.domain.Payment;
import com.hanghae.payment.infrastructure.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public Payment save(Payment payment) {
        PaymentEntity paymentEntity = paymentJpaRepository.save(PaymentEntity.from(payment));

        return paymentEntity.toDomain();
    }
}
