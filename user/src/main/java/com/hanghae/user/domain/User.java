package com.hanghae.user.domain;

import com.hanghae.common.util.EmailValidator;
import com.hanghae.user.domain.dto.request.UserCreate;
import com.hanghae.user.exception.InvalidEmailException;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

    private final Long id;
    private final String name;
    private final String email;
    private final String password;
    private final String phoneNumber;
    private final String address;
    private final boolean active;

    @Builder
    private User(Long id, String name, String email, String password, String phoneNumber, String address, boolean active) {

        if (!EmailValidator.isValid(email)) {
            throw new InvalidEmailException();
        }

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.active = active;
    }

    public static User createUser(UserCreate dto, String encryptedPassword) {
        return User.builder()
            .name(dto.name())
            .email(dto.email())
            .password(encryptedPassword)
            .phoneNumber(dto.phoneNumber())
            .address(dto.address())
            .active(false)
            .build();
    }

    public User changeActive() {
        return User.builder()
            .id(id)
            .name(name)
            .email(email)
            .password(password)
            .phoneNumber(phoneNumber)
            .address(address)
            .active(true)
            .build();
    }

}