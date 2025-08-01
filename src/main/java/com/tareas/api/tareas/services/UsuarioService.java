package com.tareas.api.tareas.services;

import com.tareas.api.tareas.persistence.entity.Usuario;
import java.util.List;

public interface UsuarioService {

    public List<Usuario> getUsuarios();
    public Usuario getUsuarioByUsername(String username);
    public Usuario editUsuario(Usuario usuario);
    public Usuario deleteUsuario(String username);
}
