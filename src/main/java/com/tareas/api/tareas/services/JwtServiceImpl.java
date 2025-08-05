package com.tareas.api.tareas.services;

import com.tareas.api.tareas.persistence.entity.Usuario;
import io.jsonwebtoken.Claims;
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

    @Override
    public String extractUsername(String token) {
        Claims jwtToken = Jwts.parserBuilder().setSigningKey(secretkey).build().parseClaimsJws(token).getBody();
        return jwtToken.getSubject();
    }

    @Override
    public boolean isTokenValid(String token, Usuario usuario) {
        String username =  extractUsername(token);
        return username.equals(usuario.getUsername()) && !isTokenExpired(token);
    }

    private String BuildToken(Usuario usuario, Long expiration) {
        return Jwts.builder()
                .setId(Integer.valueOf(usuario.getId()).toString())
                .setClaims(Map.of("name",usuario.getName()))
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

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        Claims jwtToken = Jwts.parserBuilder().setSigningKey(secretkey).build().parseClaimsJws(token).getBody();
        return jwtToken.getExpiration();
    }
}
