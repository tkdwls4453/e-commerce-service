package com.hanghae.user.application.port;

import com.hanghae.user.domain.User;
import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findById(Long id);

    void updateUserActive(String email);

    boolean existsByEmail(String email);
}
