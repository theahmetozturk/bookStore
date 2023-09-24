package com.example.bookstore.services;

import com.example.bookstore.exceptions.InvalidPasswordException;
import com.example.bookstore.exceptions.UserNotFoundException;
import com.example.bookstore.mappers.UserMapper;
import com.example.bookstore.model.dto.requests.UserLoginRequest;
import com.example.bookstore.model.dto.requests.UserSignUpRequest;
import com.example.bookstore.model.entities.User;
import com.example.bookstore.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public User singup(UserSignUpRequest request) {
        User newUser = UserMapper.mapForSaving(request);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    public String login(UserLoginRequest request) throws InvalidPasswordException {
        User userToLogin = UserMapper.mapForLogin(request);
        User existingUser = userRepository.findByEmail(userToLogin.getEmail())
                .orElseThrow(() -> new UserNotFoundException(userToLogin.getEmail()));
        if (!passwordEncoder.matches(userToLogin.getPassword(), existingUser.getPassword())) {
            throw new InvalidPasswordException("Invalid Password Error!!");
        }

        String token = Jwts.builder()
                .setSubject(existingUser.getEmail())
                .setExpiration(Date.from(Instant.now().plus(Duration.ofDays(1))))
                .signWith(SignatureAlgorithm.HS256,"secret")
                .compact();
        return token;
    }
}

