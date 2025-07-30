package com.tareas.api.tareas.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tareas.api.tareas.persistence.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Usuario getUsuarioByUsername(String username);

    public Usuario getUsuarioById(Integer id);

}
