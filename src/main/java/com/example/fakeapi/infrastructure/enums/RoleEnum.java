package com.example.fakeapi.infrastructure.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {

    ADMIN("ADMIN"),
    USER("USER");

    private String role;
}
