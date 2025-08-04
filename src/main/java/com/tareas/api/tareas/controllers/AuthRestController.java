package com.tareas.api.tareas.controllers;

import com.tareas.api.tareas.DTO.LoginRequest;
import com.tareas.api.tareas.DTO.TokenResponse;
import com.tareas.api.tareas.persistence.entity.Usuario;
import com.tareas.api.tareas.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    }

}
