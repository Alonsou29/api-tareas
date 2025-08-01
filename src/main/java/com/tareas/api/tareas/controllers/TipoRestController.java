package com.tareas.api.tareas.controllers;

import com.tareas.api.tareas.persistence.entity.Tipo;
import com.tareas.api.tareas.services.TiposServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tipo")
public class TipoRestController {

    @Autowired
    TiposServiceImpl tiposService;

    @GetMapping
    private ResponseEntity<List<?>> getAllTipos(){
        List<Tipo> tipos = tiposService.getAlltipos();
        return ResponseEntity.ok(tipos);
    }

    @PostMapping
    private ResponseEntity<?> addTipo(@RequestBody Tipo tipo){
        return ResponseEntity.ok(tiposService.addTipo(tipo));
    }

    @DeleteMapping("/{nombre}")
    private ResponseEntity<?> deleteTipo(@PathVariable String nombre){
        Tipo tp = tiposService.deleteTipo(nombre);
        return ResponseEntity.ok(tp);
    }




}
