package com.hanghae.user.presentation;

import com.hanghae.user.domain.dto.request.UserCreate;
import com.hanghae.user.domain.dto.response.UserSimpleInfo;
import com.hanghae.user.presentation.dto.request.UserCreateRequest;
import com.hanghae.user.presentation.dto.response.UserSimpleInfoResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDtoMapper {

    public static UserCreate toUserCreate(UserCreateRequest request) {
        return UserCreate.builder()
            .name(request.name())
            .email(request.email())
            .password(request.password())
            .address(request.address())
            .phoneNumber(request.phoneNumber())
            .build();
    }

    public static UserSimpleInfoResponse toUserSimpleInfoResponse(UserSimpleInfo result) {
        return UserSimpleInfoResponse.builder()
            .id(result.id())
            .name(result.name())
            .email(result.email())
            .active(result.active())
            .build();
    }

}
