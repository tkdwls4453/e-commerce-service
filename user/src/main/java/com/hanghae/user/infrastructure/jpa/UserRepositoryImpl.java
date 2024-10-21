package com.hanghae.user.infrastructure.jpa;

import com.hanghae.user.application.port.UserRepository;
import com.hanghae.user.domain.User;
import com.hanghae.user.infrastructure.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public User save(User user) {
        return userJpaRepository.save(UserEntity.from(user)).toDomain();
    }
}
