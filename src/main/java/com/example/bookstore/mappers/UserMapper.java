package com.example.bookstore.mappers;

import com.example.bookstore.model.dto.requests.UserLoginRequest;
import com.example.bookstore.model.dto.requests.UserSignUpRequest;
import com.example.bookstore.model.dto.responses.UserResponse;
import com.example.bookstore.model.entities.User;

public class UserMapper {
    public static User mapForSaving(UserSignUpRequest request) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }

    public  static UserResponse toSavedResponse(User user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    public static User mapForLogin(UserLoginRequest request) {
        return User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }
}
