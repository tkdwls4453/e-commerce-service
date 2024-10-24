package com.hanghae.user.infrastructure.repository;

import com.hanghae.user.infrastructure.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    @Modifying
    @Query(
        """
            update UserEntity user
            set user.active = true
            where user.email = :email
        """
    )
    void updateUserActiveByEmail(@Param(value = "email") String email);

    boolean existsByEmail(String email);
}
