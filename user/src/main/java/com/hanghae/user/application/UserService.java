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

    // 지금 User 도메인의 상태를 변경하지 않고 바로 데이터베이스에 쿼리를 날리는 상황, DDD 가 아닌가?
    // 그럼 User 도메인의 상태를 변경하고, 디비에 그 내용을 반영?
    public void activeUser(String email) {
        if(!userRepository.existsByEmail(email)){
            throw new NotFoundEmailException();
        }

        userRepository.updateUserActive(email);
    }
}
