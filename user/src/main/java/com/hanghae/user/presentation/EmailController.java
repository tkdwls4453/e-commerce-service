package com.hanghae.user.presentation;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.user.application.EmailService;
import com.hanghae.user.presentation.dto.request.EmailVerificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/email-verification")
    public CustomResponse<String> sendVerificationEmail(
        @RequestBody EmailVerificationRequest request
    ) {
        emailService.sendVerificationEmail(request.email());
        return CustomResponse.success("이메일을 전송했습니다.");
    }
}
