package com.tareas.api.tareas.services;

import com.tareas.api.tareas.DTO.LoginRequest;
import com.tareas.api.tareas.DTO.TokenResponse;
import com.tareas.api.tareas.persistence.entity.Usuario;

public interface AuthService {

     TokenResponse registro(Usuario usuario);
     TokenResponse login(LoginRequest request);
}
