package com.hanghae.user.infrastructure.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghae.common.response.CustomResponse;
import com.hanghae.common.util.JwtUtil;
import com.hanghae.user.application.CustomUserDetailsService;
import com.hanghae.user.domain.dto.request.UserLoginDto;
import com.hanghae.user.exception.InvalidLoginException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;
    private final CustomUserDetailsService customUserDetailsService;

    // TODO: 안보이도록 처리해야 함.
    private final Long EXPIRED_MS = 24 * 60 * 60 * 1000L;
    public AuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, ObjectMapper objectMapper, CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserLoginDto loginDto = null;

        try {
            ServletInputStream inputStream = request.getInputStream();
            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

            loginDto = objectMapper.readValue(messageBody, UserLoginDto.class);
        } catch (IOException e) {
            throw new InvalidLoginException();
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());
        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String email = ((User)authResult.getPrincipal()).getUsername();

        com.hanghae.user.domain.User user = customUserDetailsService.getUserByEmail(email);

        String jwt = jwtUtil.createJwt(user.getId(), EXPIRED_MS);

        CustomResponse<String> body = CustomResponse.success(null);

        response.addHeader("Authorization",  jwt);
        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write(objectMapper.writeValueAsString(body));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        CustomResponse<Void> errorResponse = CustomResponse.failure(
            "401",
            "로그인 실패"
        );

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
