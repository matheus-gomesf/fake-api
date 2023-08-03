package com.example.fakeapi.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private ApplicationUser user;
    private String jwt;
}