package com.hanghae.user.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserCreateRequest(

    @NotBlank(message = "이름은 필수입니다.")
    String name,

    @NotBlank(message = "이메일은 필수입니다.")
    String email,

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, message = "비밀번호는 8자리 이상힙니다")
    String password,
    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "010-0000-0000 형식을 작성해주세요")
    String phoneNumber,

    @NotBlank(message = "주소는 필수입니다.")
    String address
) {

}
