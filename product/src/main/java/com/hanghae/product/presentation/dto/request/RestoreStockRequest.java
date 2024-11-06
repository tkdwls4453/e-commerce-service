package com.hanghae.product.presentation.dto.request;

import java.util.List;
import lombok.Getter;

public record RestoreStockRequest(
    List<Info> infos
) {
    @Getter
    public static class Info{
        private Long itemId;
        private Integer quantity;
    }
}
