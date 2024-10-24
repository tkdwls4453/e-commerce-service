package com.hanghae.user.presentation;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.user.application.EmailVerificationService;
import com.hanghae.user.application.UserService;
import com.hanghae.user.presentation.dto.request.SendVerificationCodeRequest;
import com.hanghae.user.presentation.dto.request.VerifyEmailCodeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth/email-verification")
public class EmailVerificationController {

    private final EmailVerificationService emailService;
    private final UserService userService;

    @PostMapping()
    public CustomResponse<String> sendVerificationEmail(
        @RequestBody SendVerificationCodeRequest request
    ) {
        emailService.sendVerificationEmail(request.email());
        return CustomResponse.success("이메일을 전송했습니다.");
    }

    @PostMapping("/verify")
    public CustomResponse<String> verifyEmailCode(
        @RequestBody VerifyEmailCodeRequest verifyEmailCodeRequest
    ){
        if (emailService.isVerificationCodeValid(verifyEmailCodeRequest.email(), verifyEmailCodeRequest.code())) {
            userService.activeUser(verifyEmailCodeRequest.email());
            return CustomResponse.success("인증에 성공했습니다.");
        }else{
            return CustomResponse.failure("인증에 실패했습니다.");
        }
    }
}
