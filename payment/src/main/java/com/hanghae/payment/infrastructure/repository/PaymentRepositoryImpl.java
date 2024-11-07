package com.hanghae.payment.infrastructure.repository;

import com.hanghae.payment.application.port.PaymentRepository;
import com.hanghae.payment.domain.Payment;
import com.hanghae.payment.exception.NotFoundPaymentException;
import com.hanghae.payment.infrastructure.PaymentEntity;
import java.util.Optional;
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

    @Override
    public Optional<Payment> findById(Long paymentId) {
        System.out.println("paymentId = " + paymentId);
        PaymentEntity paymentEntity = paymentJpaRepository.findById(paymentId).orElse(null);
        System.out.println("paymentEntity = " + paymentEntity);

        return Optional.ofNullable(paymentEntity == null ? null : paymentEntity.toDomain());
    }

    @Override
    public void update(Payment payment) {
        PaymentEntity paymentEntity = paymentJpaRepository.findById(payment.getId()).orElseThrow(
            NotFoundPaymentException::new
        );

        paymentEntity.updateByDomain(payment);
    }
}
