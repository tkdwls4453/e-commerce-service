package com.hanghae.product.infrastructure.repository;

import com.hanghae.product.infrastructure.ItemEntity;
import com.hanghae.product.infrastructure.ProductEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemJpaRepository extends JpaRepository<ItemEntity, Long> {

    List<ItemEntity> findAllByProduct(ProductEntity productEntity);
}
