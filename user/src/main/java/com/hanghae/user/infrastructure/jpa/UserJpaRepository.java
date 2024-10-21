package com.hanghae.user.infrastructure.jpa;

import com.hanghae.user.infrastructure.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

}
