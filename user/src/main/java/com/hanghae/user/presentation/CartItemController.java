package com.hanghae.user.presentation;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.user.application.UserCartService;
import com.hanghae.user.presentation.dto.request.AddCartItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class CartItemController {
    private final UserCartService userCartService;

    @PostMapping("/{userId}/cart-items")
    public CustomResponse<String> addCartItem(
        @PathVariable Long userId,
        @RequestBody AddCartItemRequest request
    ) {
        userCartService.addCartItem(userId, request.itemId(), request.quantity());
        return CustomResponse.success("장바구니에 추가했습니다.");
    }
}
