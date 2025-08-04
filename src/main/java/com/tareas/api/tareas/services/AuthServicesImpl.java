package com.tareas.api.tareas.services;

import com.tareas.api.tareas.DTO.LoginRequest;
import com.tareas.api.tareas.DTO.TokenResponse;
import com.tareas.api.tareas.persistence.entity.Token;
import com.tareas.api.tareas.persistence.entity.Usuario;
import com.tareas.api.tareas.persistence.repository.TokenRepository;
import com.tareas.api.tareas.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServicesImpl implements AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private TokenRepository tokenRepository;


    private AuthenticationManager authenticationManager;

    @Override
    public TokenResponse registro(Usuario usuario){
        String password = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(password);
        usuarioRepository.save(usuario);
        String token = jwtService.generateToken(usuario);
        String refreshToken = jwtService.generateToken(usuario);
        saveUserToken(usuario,token);
        return new TokenResponse(token,refreshToken);
    }

    @Override
    public TokenResponse login(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            )
        );
    }

    private void saveUserToken(Usuario usuario, String jwtToken){
        Token token = new Token();
        token.setToken(jwtToken);
        token.setExpired(false);
        token.setRevoked(false);
        token.setUsuario(usuario);
        tokenRepository.save(token);
    }



}
