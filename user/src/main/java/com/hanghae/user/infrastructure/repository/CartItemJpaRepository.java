package com.hanghae.user.infrastructure.repository;

import com.hanghae.user.infrastructure.CartItemEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemJpaRepository extends JpaRepository<CartItemEntity, Long> {

    List<CartItemEntity> findByUserId(Long userId);

}
