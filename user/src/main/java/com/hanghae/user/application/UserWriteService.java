package com.hanghae.user.application;

import com.hanghae.user.application.port.UserRepository;
import com.hanghae.user.domain.User;
import com.hanghae.user.domain.dto.request.UserCreate;
import com.hanghae.user.domain.dto.response.UserSimpleInfo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserWriteService {

    private final UserRepository userRepository;


    //TODO: 패스워드 암호화 로직 필요, 스프링 시큐리티 적용시 추가 예정
    public UserSimpleInfo createUser(UserCreate userCreate) {
        User user = User.createUser(userCreate, "encryptedPassword");
        return UserSimpleInfo.from(userRepository.save(user));
    }
}
