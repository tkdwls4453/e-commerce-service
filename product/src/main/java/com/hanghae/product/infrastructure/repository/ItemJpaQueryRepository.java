package com.hanghae.product.infrastructure.repository;

import com.hanghae.product.domain.dto.response.ItemProductDto;
import com.hanghae.product.infrastructure.ItemEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemJpaQueryRepository extends JpaRepository<ItemEntity, Long> {

    @Query(
        """
        SELECT new com.hanghae.product.domain.dto.response.ItemProductDto(
        p.name, p.description, i.color, i.size, i.price
        )
        FROM ItemEntity i
        JOIN i.product p
        WHERE i.id IN :itemIds
        """
    )
    List<ItemProductDto> getItemProducts(@Param(value = "itemIds") List<Long> itemIds);
}
