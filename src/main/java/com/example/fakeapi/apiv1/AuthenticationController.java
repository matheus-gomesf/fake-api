package com.example.fakeapi.apiv1;

import com.example.fakeapi.apiv1.dto.auth.ApplicationResponseRecord;
import com.example.fakeapi.apiv1.dto.auth.ApplicationRequestRecord;
import com.example.fakeapi.business.AuthenticationService;
import com.example.fakeapi.infrastructure.entities.ApplicationUser;
import com.example.fakeapi.infrastructure.entities.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ApplicationResponseRecord> registerUser(@RequestBody ApplicationRequestRecord request) {
        ApplicationUser applicationUser = authenticationService.registerUser(request.username(), request.password());
        return ResponseEntity.ok(ApplicationResponseRecord.fromEntity(applicationUser));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody ApplicationRequestRecord request) {
        LoginResponseDTO loginResponseDTO = authenticationService.loginUser(request.username(), request.password());
        return ResponseEntity.ok(loginResponseDTO);
    }
}
