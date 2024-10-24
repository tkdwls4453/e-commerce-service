package com.hanghae.user.presentation;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.user.application.UserService;
import com.hanghae.user.domain.dto.request.UserCreate;
import com.hanghae.user.domain.dto.response.UserSimpleInfo;
import com.hanghae.user.presentation.dto.request.UserCreateRequest;
import com.hanghae.user.presentation.dto.response.UserSimpleInfoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userWriteService;

    @PostMapping
    public CustomResponse<UserSimpleInfoResponse> createUser(
        @Valid @RequestBody UserCreateRequest request
    ) {
        UserCreate userCreate = UserDtoMapper.toUserCreate(request);
        UserSimpleInfo result = userWriteService.createUser(userCreate);

        return CustomResponse.success(UserDtoMapper.toUserSimpleInfoResponse(result));
    }
    @GetMapping
    public String healthCheck(){
        return "hello";
    }
}
