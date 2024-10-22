package com.hanghae.user.application;

import com.hanghae.user.application.port.UserRepository;
import com.hanghae.user.domain.User;
import com.hanghae.user.domain.dto.request.UserCreate;
import com.hanghae.user.domain.dto.response.UserSimpleInfo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserWriteService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserSimpleInfo createUser(UserCreate userCreate) {
        User user = User.createUser(userCreate, bCryptPasswordEncoder.encode(userCreate.password()));
        return UserSimpleInfo.from(userRepository.save(user));
    }
}
