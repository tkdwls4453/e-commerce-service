package com.hanghae.user.presentation;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.user.application.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public CustomResponse<String> sendVerificationEmail(
        @RequestParam String email
    ) {
        emailService.sendVerificationEmail(email);
        return CustomResponse.success("이메일을 전송했습니다.");
    }
}
