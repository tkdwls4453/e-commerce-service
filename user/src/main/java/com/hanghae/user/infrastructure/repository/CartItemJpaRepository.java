package com.hanghae.user.infrastructure.repository;

import com.hanghae.user.infrastructure.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemJpaRepository extends JpaRepository<CartItemEntity, Long> {

}
