package com.hanghae.user.application;

import java.time.Duration;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailVerificationService {

    private final JavaMailSender mailSender;
    private final RedisTemplate<String, String> redisTemplate;

    private static final String EMAIL_KEY_PREFIX = "email:";
    public void sendVerificationEmail(String email) {
        String verificationCode = generateVerificationCode();
        saveVerificationCode(email, verificationCode);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("인증 코드");
        message.setText("인증 코드 : " + verificationCode);

        mailSender.send(message);
    }

    private String generateVerificationCode() {
        return String.valueOf(new Random().nextInt(999999 - 100000) + 100000);
    }

    private void saveVerificationCode(String email, String verificationCode){
        redisTemplate.opsForValue().set(
            EMAIL_KEY_PREFIX + email,
            verificationCode,
            Duration.ofMinutes(5)
        );
    }

    public boolean isVerificationCodeValid(String email, String verificationCode) {
        String key = EMAIL_KEY_PREFIX + email;
        String value = redisTemplate.opsForValue().get(key);

        if(value != null && value.equals(verificationCode)){
            redisTemplate.delete(key);
            return true;
        }

        return false;
    }
}
