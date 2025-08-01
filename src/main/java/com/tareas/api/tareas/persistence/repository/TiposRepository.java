package com.tareas.api.tareas.persistence.repository;

import com.tareas.api.tareas.persistence.entity.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiposRepository extends JpaRepository<Tipo, Integer> {
    Tipo findByNombre(String nombre);
}
