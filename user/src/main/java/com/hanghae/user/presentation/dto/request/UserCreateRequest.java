package com.hanghae.user.presentation.dto.request;

import lombok.Builder;

//TODO: 유효성 검사하는 로직이 필요
// 테스트 편의상 아지 유효성 검사를 하지 않는다. Ex) 패스워드는 8자 이상 특수문자, 대문자 포함...
@Builder
public record UserCreateRequest(

    String name,
    String email,
    String password,
    String phoneNumber,
    String address
) {

}
