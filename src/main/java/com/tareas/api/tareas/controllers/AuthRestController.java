package com.tareas.api.tareas.controllers;

import com.tareas.api.tareas.DTO.LoginRequest;
import com.tareas.api.tareas.DTO.TokenResponse;
import com.tareas.api.tareas.persistence.entity.Usuario;
import com.tareas.api.tareas.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthRestController {

    @Autowired
    AuthService authService;

    @PostMapping("/registro")
    private ResponseEntity<?> registroUser(@RequestBody Usuario usuario) {
        TokenResponse token = authService.registro(usuario);
        return ResponseEntity.ok(token);
    }


    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        TokenResponse token = authService.login(loginRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    private ResponseEntity<?> refresh(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        TokenResponse tkr = authService.refresh(token);
        return ResponseEntity.ok(tkr);
    }
}
