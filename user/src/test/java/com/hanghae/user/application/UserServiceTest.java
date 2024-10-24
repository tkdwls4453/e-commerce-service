package com.hanghae.user.application;

import static org.assertj.core.api.Assertions.*;

import com.hanghae.user.application.port.UserRepository;
import com.hanghae.user.domain.User;
import com.hanghae.user.domain.dto.request.UserCreate;
import com.hanghae.user.domain.dto.response.UserSimpleInfo;
import com.hanghae.user.mock.FakeUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class UserServiceTest {

    private final UserRepository userRepository = new FakeUserRepository();
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final UserService userWriteService = new UserService(userRepository, bCryptPasswordEncoder);


    @Test
    @DisplayName("유저 정보로 유저를 생성시, 유저를 저장후 리턴한다.")
    void givenUserInfo_whenCreateUser_thenSaveUserAndReturnUserInfo() {
        // Given
        String email = "email@naver.com";
        UserCreate dto = UserCreate.builder()
            .name("user")
            .address("seoul")
            .email(email)
            .password("password")
            .phoneNumber("010-1234-1234")
            .build();

        // When
        UserSimpleInfo simpleInfo = userWriteService.createUser(dto);

        // Then
        User user = userRepository.findById(1L).orElse(null);
        assertThat(user).isNotNull();
        assertThat(simpleInfo.id()).isEqualTo(user.getId());
        assertThat(simpleInfo.name()).isEqualTo(user.getName());
        assertThat(simpleInfo.email()).isEqualTo(user.getEmail());
        assertThat(simpleInfo.active()).isEqualTo(user.isActive());
        assertThat(user.getPassword()).isNotEqualTo("password");
    }
}