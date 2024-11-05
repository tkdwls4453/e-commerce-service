package com.hanghae.payment.application.port;

import com.hanghae.payment.domain.Payment;

public interface PaymentRepository {

    Payment save(Payment payment);
}
