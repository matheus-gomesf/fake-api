package com.example.fakeapi.configurations;

import com.example.fakeapi.infrastructure.entities.ApplicationUser;
import com.example.fakeapi.infrastructure.entities.Role;
import com.example.fakeapi.infrastructure.enums.RoleEnum;
import com.example.fakeapi.infrastructure.repositories.RoleRepository;
import com.example.fakeapi.infrastructure.repositories.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "users.registration")
public class SystemInitialize {

    private String adminUsername;
    private String adminPwd;
    private List<String> roles;

    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            log.info("Initialize user registration");

            if (roleRepository.findByAuthority(RoleEnum.ADMIN.getRole()).isPresent()) {
                log.info("Finish initialize user registration");
                return;
            }

            roles.forEach(role -> roleRepository.save(new Role(role)));

            Optional<Role> adminRole = roleRepository.findByAuthority(RoleEnum.ADMIN.getRole());

            ApplicationUser admin = new ApplicationUser(UUID.randomUUID(), adminUsername, passwordEncoder.encode(adminPwd), Set.of(adminRole.get()));
            userRepository.save(admin);
        };
    }

}
