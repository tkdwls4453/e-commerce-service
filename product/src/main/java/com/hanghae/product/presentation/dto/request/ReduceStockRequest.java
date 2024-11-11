package com.hanghae.product.presentation.dto.request;

import java.util.List;
import lombok.Getter;

public record ReduceStockRequest(
    List<Info> infos
) {
    @Getter
    public static class Info{
        private Long itemId;
        private Integer quantity;

        public Info(long itemId, int quantity) {
            this.itemId = itemId;
            this.quantity = quantity;
        }
    }
}
