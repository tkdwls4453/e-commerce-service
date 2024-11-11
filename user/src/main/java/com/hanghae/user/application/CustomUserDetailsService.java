package com.hanghae.user.application;

import com.hanghae.user.application.port.UserRepository;
import com.hanghae.user.domain.User;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElse(null);

        if(user == null){
            throw new UsernameNotFoundException(email);
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
            true, true, true, true,
            new ArrayList<>());
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);

        if(user == null){
            throw new UsernameNotFoundException(email);
        }

        return user;
    }
}
