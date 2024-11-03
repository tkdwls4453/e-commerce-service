package com.hanghae.user.application;

import com.hanghae.user.application.port.CartItemRepository;
import com.hanghae.user.domain.CartItem;
import com.hanghae.user.domain.dto.request.CartItemDto;
import com.hanghae.user.domain.dto.response.ItemInfo;
import com.hanghae.user.exception.InvalidQuantityException;
import com.hanghae.user.application.client.ItemClient;

import com.hanghae.user.application.client.response.ItemProductResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserCartService {

    private final CartItemRepository cartItemRepository;
    private final ItemClient itemClient;

    public void addCartItem(Long userId, Long itemId, int quantity){

        ItemInfo itemInfo = itemClient.getItemInfo(itemId).getData();

        if (itemInfo.stockQuantity() < quantity) {
            throw new InvalidQuantityException();
        }

        cartItemRepository.createAndAddCartItem(userId, itemId, quantity);
    }

    public List<CartItemDto> getCartItems(Long userId) {

        List<CartItem> cartItems = cartItemRepository.getCartItems(userId);

        List<Long> itemIds = cartItems.stream().map(CartItem::getId).toList();

        List<ItemProductResponse> data = itemClient.getItemProducts(itemIds).getData();
        System.out.println(data);

        List<CartItemDto> result = new ArrayList<>();

        for(int i=0; i<cartItems.size(); i++) {
            CartItemDto cartItemDto = CartItemDto.builder()
                .id(cartItems.get(i).getId())
                .quantity(cartItems.get(i).getQuantity())
                .color(data.get(i).color())
                .price(data.get(i).price())
                .description(data.get(i).description())
                .productName(data.get(i).productName())
                .itemId(cartItems.get(i).getItemId())
                .quantity(cartItems.get(i).getQuantity())
                .size(data.get(i).size())
                .build();

            result.add(cartItemDto);
        }

        return result;
    }
}
