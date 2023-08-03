package com.example.fakeapi.business;

import com.example.fakeapi.exceptions.RoleException;
import com.example.fakeapi.exceptions.UserException;
import com.example.fakeapi.infrastructure.entities.ApplicationUser;
import com.example.fakeapi.infrastructure.entities.LoginResponseDTO;
import com.example.fakeapi.infrastructure.entities.Role;
import com.example.fakeapi.infrastructure.repositories.RoleRepository;
import com.example.fakeapi.infrastructure.repositories.UserRepository;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public ApplicationUser registerUser(String username, String password) {
        String encodePassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").orElseThrow(RoleException::new);

        ApplicationUser applicationUser = new ApplicationUser(UUID.randomUUID(), username, encodePassword, Set.of(userRole));
        try {
            userRepository.save(applicationUser);
        } catch (DataIntegrityViolationException e) {
            throw new UserException("User already exist", e, HttpStatus.BAD_REQUEST);
        }
        return applicationUser;
    }

    public LoginResponseDTO loginUser(String username, String password) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(authenticate);

            return new LoginResponseDTO(userRepository.findApplicationUserByUsername(username).get(), token);
        } catch (AuthenticationException e) {
            return new LoginResponseDTO(null, "");
        }
    }
}
