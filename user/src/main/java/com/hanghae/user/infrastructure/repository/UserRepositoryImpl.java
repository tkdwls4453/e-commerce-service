package com.hanghae.user.infrastructure.repository;

import com.hanghae.user.application.port.UserRepository;
import com.hanghae.user.domain.User;
import com.hanghae.user.exception.NotFoundUserException;
import com.hanghae.user.infrastructure.UserEntity;
import java.util.Optional;
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

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id).map(UserEntity::toDomain);
    }

    @Override
    public void updateUserActive(String email) {
        userJpaRepository.updateUserActiveByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        UserEntity userEntity = userJpaRepository.findByEmail(email).orElse(null);

        return userEntity == null ? Optional.empty() : Optional.of(userEntity.toDomain());
    }

    @Override
    public void updateUser(User updatedUser) {
        UserEntity userEntity = userJpaRepository.findById(updatedUser.getId()).orElseThrow(
            NotFoundUserException::new
        );

        userEntity.update(updatedUser);
    }

}
