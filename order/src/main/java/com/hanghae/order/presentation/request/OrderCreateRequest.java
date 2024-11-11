package com.hanghae.order.presentation.request;

import com.hanghae.order.domain.dto.request.OrderCreateDto;
import java.util.List;

public record OrderCreateRequest(
    List<OrderCreateDto> infos
) {

}


