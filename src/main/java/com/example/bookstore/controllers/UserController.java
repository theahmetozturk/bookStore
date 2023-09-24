package com.example.bookstore.controllers;

import com.example.bookstore.exceptions.InvalidPasswordException;
import com.example.bookstore.mappers.UserMapper;
import com.example.bookstore.model.dto.requests.UserLoginRequest;
import com.example.bookstore.model.dto.requests.UserSignUpRequest;
import com.example.bookstore.model.dto.responses.UserResponse;
import com.example.bookstore.model.entities.User;
import com.example.bookstore.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/singup")
    public ResponseEntity<UserResponse> singup(@RequestBody UserSignUpRequest request) {
        User user = userService.singup(request);
        UserResponse response = UserMapper.toSavedResponse(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest request) throws InvalidPasswordException {
        String token = userService.login(request);
        return ResponseEntity.ok(token);
    }
}
