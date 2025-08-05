package com.tareas.api.tareas.persistence.repository;

import com.tareas.api.tareas.persistence.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    List<Token> getTokensByExpiredAndRevokedAndUsuario_id(boolean expired, boolean revoked, int idUsuario);
    Token findByToken(String token);


}
