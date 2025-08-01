package com.tareas.api.tareas.controllers;

import com.tareas.api.tareas.persistence.entity.Tarea;
import com.tareas.api.tareas.persistence.entity.Tipo;
import com.tareas.api.tareas.services.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarea")
public class TareaRestController {

    @Autowired
    private TareaService tareaService;

    @GetMapping("/{username}")
    private ResponseEntity<?> getTareas(@PathVariable String username){
        List<Tarea> tareas = tareaService.getTareasByUsername(username);
        return ResponseEntity.ok(tareas);
    }

    @PostMapping("/{username}/{tipo}")
    private ResponseEntity<?> addTarea(@PathVariable String username, @PathVariable String tipo, @RequestBody Tarea tarea){
        Tarea tr = tareaService.addTarea(username,tipo,tarea);
        return ResponseEntity.ok(tr);
    }

    @PutMapping
    private ResponseEntity<?> updateTareas(@RequestBody Tarea tarea){
        tareaService.updateTarea(tarea);
        return ResponseEntity.ok(tarea);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteTareas(@PathVariable Integer id){
        return ResponseEntity.ok(tareaService.deleteTarea(id));
    }
}
