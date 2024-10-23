package com.hanghae.product.infrastructure;

import com.hanghae.common.infrastructure.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

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

}
