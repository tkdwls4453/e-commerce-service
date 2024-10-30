package com.hanghae.product.presentation.dto.request;

import java.util.List;

public record GetItemProductRequest(
    List<Long> itemIds
) {

}
