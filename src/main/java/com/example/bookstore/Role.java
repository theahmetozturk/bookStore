package com.example.bookstore;

import lombok.*;
import lombok.experimental.SuperBuilder;

@ToString
public enum Role {
    ADMIN,
    USER;
    private Role() {
    }
    public static Role of (String name) {
        return Enum.valueOf(Role.class, name);
    }
}
