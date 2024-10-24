package com.hanghae.user.presentation.dto.request;

public record VerifyEmailCodeRequest(
    String email,
    String code
) {

}
