package com.hanghae.user.presentation;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.user.application.UserCartService;
import com.hanghae.user.domain.dto.request.CartItemDto;
import com.hanghae.user.presentation.dto.request.AddCartItemRequest;
import com.hanghae.user.presentation.dto.response.CartItemResponse;
import com.hanghae.user.presentation.mapper.CartItemDtoMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class ExternalCartItemController {
    private final UserCartService userCartService;

    @PostMapping("/{userId}/cart-items")
    public CustomResponse<String> addCartItem(
        @PathVariable Long userId,
        @RequestBody AddCartItemRequest request
    ) {
        userCartService.addCartItem(userId, request.itemId(), request.quantity());
        return CustomResponse.success("장바구니에 추가했습니다.");
    }

    @GetMapping("/{userId}/cart-items")
    public CustomResponse<List<CartItemResponse>> getCartItems(
        @PathVariable Long userId
    ){
        List<CartItemDto> cartItems = userCartService.getCartItems(userId);

        List<CartItemResponse> data = cartItems.stream()
            .map(CartItemDtoMapper::toCartItemResponse).toList();

        return CustomResponse.success(data);
    }
}
