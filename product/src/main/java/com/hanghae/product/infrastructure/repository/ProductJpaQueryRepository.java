package com.hanghae.product.infrastructure.repository;

import com.hanghae.product.infrastructure.ProductEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductJpaQueryRepository extends JpaRepository<ProductEntity, Long> {

    @Query(
        """
        SELECT p
        FROM ProductEntity p
        WHERE p.active = true AND (:cursor is null or p.id < :cursor)
        ORDER BY p.id DESC
        """
    )
    List<ProductEntity> findAllByCursor(@Param("cursor") Long cursor, Pageable pageable);

    @Query(
        """
        SELECT COUNT(p)
        FROM ProductEntity p
        WHERE p.active = true
        """
    )
    int countIsActive();
}
