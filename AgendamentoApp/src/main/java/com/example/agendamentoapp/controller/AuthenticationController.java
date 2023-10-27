package com.example.agendamentoapp.controller;

import com.example.agendamentoapp.dto.AuthenticationDTO;
import com.example.agendamentoapp.dto.LoginResponseDTO;
import com.example.agendamentoapp.dto.RegisterDTO;
import com.example.agendamentoapp.repository.UserRepository;
import com.example.agendamentoapp.security.TokenService;
import com.example.agendamentoapp.user.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private UserRepository repository;
    private TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository repository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO registerDTO) {
        if (registerDTO.getPassword() == null || registerDTO.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Senha não pode ser nula.");
        }

        if (this.repository.findByLogin(registerDTO.getLogin()) != null) {
            return ResponseEntity.badRequest().body("Um usuário com o mesmo login já existe.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.getPassword());
        User newUser = new User(registerDTO.getLogin(), encryptedPassword, registerDTO.getRole());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
