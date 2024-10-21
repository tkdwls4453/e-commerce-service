package com.hanghae.user.application.port;

import com.hanghae.user.domain.User;

public interface UserRepository {
    User save(User user);
}
