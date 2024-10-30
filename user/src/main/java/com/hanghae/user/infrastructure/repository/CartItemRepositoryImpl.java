package com.hanghae.user.infrastructure.repository;

import com.hanghae.user.application.port.CartItemRepository;
import com.hanghae.user.domain.CartItem;
import com.hanghae.user.domain.dto.request.CartItemDto;
import com.hanghae.user.exception.NotFoundUserException;
import com.hanghae.user.infrastructure.CartItemEntity;
import com.hanghae.user.infrastructure.UserEntity;
import com.hanghae.user.infrastructure.client.ItemClient;
import com.hanghae.user.infrastructure.client.request.GetItemProductRequest;
import com.hanghae.user.infrastructure.client.response.ItemProductResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CartItemRepositoryImpl implements CartItemRepository {

    private final CartItemJpaRepository cartItemJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final ItemClient itemClient;

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
