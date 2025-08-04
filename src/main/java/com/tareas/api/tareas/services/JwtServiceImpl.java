package com.tareas.api.tareas.services;

import com.tareas.api.tareas.persistence.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretkey;
    @Value("${application.security.jwt.expiration}")
    private Long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private Long refreshExpiration;

    public String generateToken(Usuario usuario) {
        return BuildToken(usuario,jwtExpiration);
    }

    public String refreshToken(Usuario usuario) {
        return  BuildToken(usuario,refreshExpiration);
    }

    private String BuildToken(Usuario usuario, Long expiration) {
        return Jwts.builder()
                .setId(Integer.valueOf(usuario.getId()).toString())
                .setClaims(Map.of("name", usuario.getName()))
                .setSubject(usuario.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
