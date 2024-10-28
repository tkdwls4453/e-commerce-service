package com.hanghae.user.application;

import com.hanghae.user.application.port.UserRepository;
import com.hanghae.user.domain.User;
import com.hanghae.user.domain.dto.request.UserCreate;
import com.hanghae.user.domain.dto.response.UserSimpleInfo;

import com.hanghae.user.exception.DuplicatedEmailException;
import com.hanghae.user.exception.NotFoundEmailException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserSimpleInfo createUser(UserCreate userCreate) {
        if(userRepository.existsByEmail(userCreate.email())){
            throw new DuplicatedEmailException();
        }
        User user = User.createUser(userCreate, bCryptPasswordEncoder.encode(userCreate.password()));
        return UserSimpleInfo.from(userRepository.save(user));
    }

    public void activeUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
            NotFoundEmailException::new
        );

        User updatedUser = user.changeActive();
        userRepository.updateUser(updatedUser);
    }
}
