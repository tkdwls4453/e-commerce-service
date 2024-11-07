package com.hanghae.payment.application.port;

import com.hanghae.payment.domain.Payment;
import java.util.Optional;

public interface PaymentRepository {

    Payment save(Payment payment);

    Optional<Payment> findById(Long paymentId);

    void update(Payment payment);
}
