package com.tareas.api.tareas.configurations;

import com.tareas.api.tareas.persistence.entity.Token;
import com.tareas.api.tareas.persistence.entity.Usuario;
import com.tareas.api.tareas.persistence.repository.TokenRepository;
import com.tareas.api.tareas.persistence.repository.UsuarioRepository;
import com.tareas.api.tareas.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException , IOException {
        if(request.getServletPath().contains("/auth")){
            chain.doFilter(request,response);
            return;
        }
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(header == null || !header.startsWith("Bearer ")){
            chain.doFilter(request,response);
            return;
        }
        String jwtToken = header.substring(7);
        String username = jwtService.extractUsername(jwtToken);

        if(username == null || SecurityContextHolder.getContext().getAuthentication() != null){
            return;
        }

        Token tok = tokenRepository.findByToken(jwtToken);
        if(tok == null || tok.isExpired() || tok.isRevoked()){
            chain.doFilter(request,response);
            return;
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        Usuario usuario = usuarioRepository.getUsuarioByUsername(username);

        if(usuario == null){
            chain.doFilter(request,response);
            return;
        }

        boolean isTokenValid = jwtService.isTokenValid(jwtToken, usuario);
        if(!isTokenValid){
            return;
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        chain.doFilter(request,response);
    }


}
