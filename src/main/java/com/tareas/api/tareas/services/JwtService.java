package com.tareas.api.tareas.services;

import com.tareas.api.tareas.persistence.entity.Usuario;

public interface JwtService {

    String generateToken(Usuario usuario);
    String refreshToken(Usuario usuario);
    String extractUsername(String token);
    boolean isTokenValid(String token, Usuario usuario);
}
