package com.hanghae.product.infrastructure.repository;

import com.hanghae.product.infrastructure.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemCommandJpaRepository extends JpaRepository<ItemEntity, Long> {

    @Modifying
    @Query(
        """
        UPDATE ItemEntity i
        SET i.stockQuantity = i.stockQuantity - :quantity
        WHERE i.id = :itemId
        """
    )
    void reduceStock(@Param("itemId") Long itemId, @Param("quantity") Integer quantity);

    @Modifying
    @Query(
        """
        UPDATE ItemEntity i
        SET i.stockQuantity = i.stockQuantity + :quantity
        WHERE i.id = :itemId
        """
    )
    void increaseStock(@Param("itemId")Long itemId, @Param("quantity") Integer quantity);
}
