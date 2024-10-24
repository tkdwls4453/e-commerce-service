package com.hanghae.user.mock;

import com.hanghae.user.application.port.UserRepository;
import com.hanghae.user.domain.User;
import com.hanghae.user.infrastructure.UserEntity;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class FakeUserRepository implements UserRepository {

    private final Map<Long, UserEntity> store = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public User save(User user) {
        Long id = idCounter.getAndIncrement();

        UserEntity userEntity = UserEntity.from(user);
        userEntity.updateId(id);

        store.put(id, userEntity);
        return userEntity.toDomain();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.of(store.get(id).toDomain());
    }

    @Override
    public void updateUserActive(String email) {

    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

}
