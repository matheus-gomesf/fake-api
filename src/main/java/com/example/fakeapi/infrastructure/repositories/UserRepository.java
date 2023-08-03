package com.example.fakeapi.infrastructure.repositories;

import com.example.fakeapi.infrastructure.entities.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<ApplicationUser, UUID> {

    Optional<ApplicationUser> findApplicationUserByUsername(String username);
}
