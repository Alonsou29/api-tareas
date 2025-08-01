package com.tareas.api.tareas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tareas.api.tareas.persistence.entity.Usuario;
import com.tareas.api.tareas.persistence.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TareaService tareaService;

    public List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioByUsername(String username) {
        return usuarioRepository.getUsuarioByUsername(username);
    }

    @Override
    public Usuario editUsuario(Usuario usuario) {
        Usuario us = usuarioRepository.getUsuarioById(usuario.getId());
        if(us!=null){
            us.setName(usuario.getName());
            us.setUsername(usuario.getUsername());
            us.setPassword(usuario.getPassword());
            usuarioRepository.save(us);
            return us;
        }
        return null;
    }

    @Override
    public Usuario deleteUsuario(String username) {
        try{
            Usuario us = usuarioRepository.getUsuarioByUsername(username);
            tareaService.deleteTareaByUserId(us.getId());
            usuarioRepository.delete(us);
            return us;
        }catch(Exception e){
            return null;
        }
    }
}
