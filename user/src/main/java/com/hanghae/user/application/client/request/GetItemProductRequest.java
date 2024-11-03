package com.hanghae.user.application.client.request;

import java.util.List;
import lombok.Builder;

@Builder
public record GetItemProductRequest(
    List<Long> itemIds
) {

}
