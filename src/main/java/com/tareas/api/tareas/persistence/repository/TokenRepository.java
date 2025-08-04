package com.tareas.api.tareas.persistence.repository;

import com.tareas.api.tareas.persistence.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
}
