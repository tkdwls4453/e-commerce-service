package com.hanghae.user.infrastructure.repository;

import com.hanghae.user.application.port.CartItemRepository;
import com.hanghae.user.domain.CartItem;
import com.hanghae.user.exception.NotFoundUserException;
import com.hanghae.user.infrastructure.CartItemEntity;
import com.hanghae.user.infrastructure.UserEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CartItemRepositoryImpl implements CartItemRepository {

    private final CartItemJpaRepository cartItemJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Override
    public void createAndAddCartItem(Long userId, Long productId, int quantity) {
        UserEntity userEntity = userJpaRepository.findById(userId).orElseThrow(
            NotFoundUserException::new
        );

        CartItemEntity cartItemEntity = CartItemEntity.builder()
            .user(userEntity)
            .itemId(productId)
            .quantity(quantity)
            .build();

        cartItemJpaRepository.save(cartItemEntity);
    }

    @Override
    public List<CartItem> getCartItems(Long userId) {
        List<CartItemEntity> cartItemEntities = cartItemJpaRepository.findByUserId(userId);
        return cartItemEntities.stream().map(CartItemEntity::toDomain).toList();
    }
}
