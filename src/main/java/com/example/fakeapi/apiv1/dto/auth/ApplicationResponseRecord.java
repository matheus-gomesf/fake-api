package com.example.fakeapi.apiv1.dto.auth;

import com.example.fakeapi.infrastructure.entities.ApplicationUser;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

public record ApplicationResponseRecord(
        UUID userId,
        String username,
        Collection<? extends GrantedAuthority> authorities
) {

    public static ApplicationResponseRecord fromEntity(ApplicationUser entity) {
        return new ApplicationResponseRecord(entity.getUserId(), entity.getUsername(), entity.getAuthorities());
    }
}
