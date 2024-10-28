package com.hanghae.user.application;

import com.hanghae.user.application.port.CartItemRepository;
import com.hanghae.user.domain.dto.response.ItemInfo;
import com.hanghae.user.exception.InvalidQuantityException;
import com.hanghae.user.infrastructure.client.ItemClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@RequiredArgsConstructor
@Service
public class UserCartService {

    private final CartItemRepository cartItemRepository;
    private final ItemClient itemClient;

    public void addCartItem(Long userId, Long itemId, int quantity){

        ItemInfo itemInfo = itemClient.getItemInfo(itemId).getData();

        System.out.println(itemInfo);

        if (itemInfo.stockQuantity() < quantity) {
            throw new InvalidQuantityException();
        }

        cartItemRepository.createAndAddCartItem(userId, itemId, quantity);
    }
}
