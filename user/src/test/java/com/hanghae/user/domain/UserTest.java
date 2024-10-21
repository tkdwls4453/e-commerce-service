package com.hanghae.user.domain;

import static org.assertj.core.api.Assertions.*;

import com.hanghae.user.domain.dto.request.UserCreate;
import com.hanghae.user.exception.InvalidEmailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    @DisplayName("유저 정보가 주어지면 유저를 만들어 반환한다.")
    void givenUserInfo_whenCreateUser_thenReturnUser(){
        // Given
        String encryptedPassword = "thisisencryptedPassword";
        String email = "email@naver.com";
        UserCreate dto = UserCreate.builder()
            .name("user")
            .address("seoul")
            .email(email)
            .password("password")
            .phoneNumber("010-1234-1234")
            .build();

        // When
        User user = User.createUser(dto, encryptedPassword);

        // Then
        assertThat(user.getName()).isEqualTo("user");
        assertThat(user.getAddress()).isEqualTo("seoul");
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo(encryptedPassword);
        assertThat(user.getPhoneNumber()).isEqualTo("010-1234-1234");

    }

    @Test
    @DisplayName("형식이 맞지 않는 이메일로 유저 생성시 예외를 반환한다.")
    void givenInvalidEmail_whenCreateUser_thenException(){
        // Given
        String encryptedPassword = "thisisencryptedPassword";
        String invalidEmail = "email";
        UserCreate dto = UserCreate.builder()
            .name("user")
            .address("seoul")
            .email(invalidEmail)
            .password("password")
            .phoneNumber("010-1234-1234")
            .build();

        // When Then
        assertThatThrownBy(() -> {
                User.createUser(dto, encryptedPassword);
            }
        )
            .isInstanceOf(InvalidEmailException.class);

    }
}