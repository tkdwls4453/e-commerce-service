package com.hanghae.product.infrastructure;

import com.hanghae.common.infrastructure.BaseEntity;
import com.hanghae.product.domain.ItemStatus;
import com.hanghae.product.domain.dto.response.ItemDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "items")
@Entity
public class ItemEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String color;
    private Integer size;

    @Column(nullable = false)
    private Integer stockQuantity;

    @Column(nullable = false)
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity product;

    public ItemDto toDto() {
        String status;
        if(getStockQuantity() == 0) status = ItemStatus.SOLD_OUT.getDescription();
        else if(getStockQuantity() >= 10) status = ItemStatus.MORE_THAN_TEN.getDescription();
        else status = ItemStatus.SOLD_OUT.getDescription();

        return ItemDto.builder()
            .id(this.id)
            .price(this.price)
            .color(this.color)
            .size(this.size)
            .status(status)
            .build();
    }

}
