package com.hanghae.user.infrastructure;

import com.hanghae.common.infrastructure.BaseEntity;
import com.hanghae.user.domain.CartItem;
import com.hanghae.user.exception.InvalidQuantityException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "cart")
public class CartItemEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity user;

    private Long ItemId;

    private Integer quantity;

    @Builder
    private CartItemEntity(UserEntity user, Long itemId, Integer quantity) {

        if (quantity <= 0) {
            throw new InvalidQuantityException();
        }

        this.user = user;
        this.ItemId = itemId;
        this.quantity = quantity;
    }

    public CartItem toDomain(){
        return CartItem.builder()
            .id(id)
            .itemId(ItemId)
            .quantity(quantity)
            .user(user.toDomain())
            .build();
    }

}
