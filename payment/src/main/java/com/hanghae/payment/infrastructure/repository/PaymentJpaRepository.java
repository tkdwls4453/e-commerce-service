package com.hanghae.payment.infrastructure.repository;

import com.hanghae.payment.infrastructure.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Long> {

}
