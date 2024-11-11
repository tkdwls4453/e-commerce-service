package com.hanghae.order.application.client.request;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
public record ReduceStockRequest(
    List<Info> infos
) {
    @Getter
    public static class Info{
        private Long itemId;
        private Integer quantity;

        @Builder
        public Info(Long itemId, Integer quantity) {
            this.itemId = itemId;
            this.quantity = quantity;
        }
    }
}
