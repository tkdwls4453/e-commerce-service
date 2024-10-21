package com.hanghae.user.presentation.dto.response;

import lombok.Builder;

@Builder
public record UserSimpleInfoResponse(
     Long id,
     String name,
     String email,
     boolean active
) {

}
