package com.hanghae.user.domain.dto.request;

import lombok.Builder;

@Builder
public record UserCreate(
    String name,
    String email,
    String password,
    String phoneNumber,
    String address
) {
}
