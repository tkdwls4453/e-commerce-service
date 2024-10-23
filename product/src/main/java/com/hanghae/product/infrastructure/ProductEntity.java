package com.hanghae.product.infrastructure;

import com.hanghae.common.infrastructure.BaseEntity;
import com.hanghae.product.domain.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "products")
@Entity
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)

    private Integer price;

    @Column(nullable = false)
    private boolean active;

    @Builder
    private ProductEntity(Long id, String name, String description, Integer price, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = active;
    }

    public Product toDomain() {
        return Product.builder()
            .id(this.id)
            .name(this.name)
            .description(this.description)
            .price(this.price)
            .active(this.active)
            .build();
    }
}
