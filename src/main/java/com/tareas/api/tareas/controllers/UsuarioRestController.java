package com.tareas.api.tareas.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tareas.api.tareas.persistence.entity.Usuario;
import com.tareas.api.tareas.services.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios(){
        List<Usuario> usuarios = usuarioService.getUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{username}")
    private ResponseEntity<Usuario> getUsuarioByUsername(@PathVariable String username){
        Usuario us = usuarioService.getUsuarioByUsername(username);
        return ResponseEntity.ok(us);
    }

    @PutMapping("/edit")
    private ResponseEntity<Usuario> editarUsuario(@RequestBody Usuario usuario){
        usuarioService.editUsuario(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{username}")
    private ResponseEntity<Usuario> deleteUsuario(@PathVariable String username){
        Usuario us = usuarioService.deleteUsuario(username);
        return ResponseEntity.ok(us);
    }

}
