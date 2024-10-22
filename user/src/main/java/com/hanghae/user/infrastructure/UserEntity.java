package com.hanghae.user.infrastructure;

import com.hanghae.common.infrastructure.BaseEntity;
import com.hanghae.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private boolean active;

    @Builder
    private UserEntity(String name, String email, String password, String phoneNumber,
        String address, boolean active) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.active = active;
    }

    public static UserEntity from(User user) {
        return UserEntity.builder()
            .name(user.getName())
            .email(user.getEmail())
            .password(user.getPassword())
            .phoneNumber(user.getPhoneNumber())
            .address(user.getAddress())
            .active(user.isActive())
            .build();
    }

    public User toDomain() {
        return User.builder()
            .id(this.id)
            .name(this.name)
            .email(this.email)
            .password(this.password)
            .phoneNumber(this.phoneNumber)
            .address(this.address)
            .active(this.active)
            .build();
    }

    public void updateId(Long id){
        this.id = id;
    }
}
