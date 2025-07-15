package com.example.OrganizaAi.controllers;

import com.example.OrganizaAi.dto.LoginRequestDTO;
import com.example.OrganizaAi.dto.LoginResponseDTO;
import com.example.OrganizaAi.dto.RegisterRequestDTO;
import com.example.OrganizaAi.models.UserModel;
import com.example.OrganizaAi.repositories.UserRepository;
import com.example.OrganizaAi.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthController(UserRepository repository,
                          PasswordEncoder passwordEncoder,
                          TokenService tokenService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        UserModel user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
        Optional<UserModel> user = this.repository.findByEmail(body.email());
        if (user.isEmpty()) {
            UserModel newUser = new UserModel();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            this.repository.save(newUser);
            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new LoginResponseDTO(newUser.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
