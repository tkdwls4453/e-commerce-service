package com.hanghae.user.domain.dto.response;

public record ItemInfo(
    Long id,
    String color,
    Integer size,
    Integer stockQuantity,
    Integer price
){

}
