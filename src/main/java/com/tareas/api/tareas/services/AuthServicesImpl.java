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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    public AuthenticationManager authenticationManager;

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
        Usuario usuario = usuarioRepository.getUsuarioByUsername(request.getUsername());
        String jwt = jwtService.generateToken(usuario);
        String refresh = jwtService.refreshToken(usuario);
        revokeAllUserToken(usuario);
        saveUserToken(usuario,jwt);
        return new TokenResponse(jwt,refresh);
    }

    @Override
    public TokenResponse refresh(String token) {
        if(token == null || !token.startsWith("Bearer ")){
            throw new IllegalArgumentException("Token invalido");
        }

        String refreshToken = token.substring(7);
        String username = jwtService.extractUsername(refreshToken);

        if(username == null){
            throw new IllegalArgumentException("Username invalido");
        }
        Usuario usuario = usuarioRepository.getUsuarioByUsername(username);
        if(usuario == null){
            throw new UsernameNotFoundException(username);
        }
        if(!jwtService.isTokenValid(refreshToken,usuario)){
            throw new IllegalArgumentException("Token invalido");
        }
        String accessToken = jwtService.generateToken(usuario);
        revokeAllUserToken(usuario);
        saveUserToken(usuario,accessToken);

        return new TokenResponse(accessToken,refreshToken);
    }

    private void saveUserToken(Usuario usuario, String jwtToken){
        Token token = new Token();
        token.setToken(jwtToken);
        token.setExpired(false);
        token.setRevoked(false);
        token.setUsuario(usuario);
        tokenRepository.save(token);
    }

    private void revokeAllUserToken(Usuario usuario){
        List<Token> validUserTokens = tokenRepository.getTokensByExpiredAndRevokedAndUsuario_id(false,false,usuario.getId());
        if(!validUserTokens.isEmpty()){
            for(Token token:validUserTokens){
                token.setRevoked(true);
                token.setExpired(true);
            }
            tokenRepository.saveAll(validUserTokens);
        }


    }



}
