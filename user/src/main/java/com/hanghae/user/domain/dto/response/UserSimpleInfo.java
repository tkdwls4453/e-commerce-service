package com.hanghae.user.domain.dto.response;

import com.hanghae.user.domain.User;
import lombok.Builder;

@Builder
public record UserSimpleInfo(
    Long id,
    String name,
    String email,
    boolean active
) {
    public static UserSimpleInfo from(User user) {
        return UserSimpleInfo.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .active(user.isActive())
            .build();
    }

}
