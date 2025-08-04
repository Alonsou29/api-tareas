package com.tareas.api.tareas.controllers;

import com.tareas.api.tareas.DTO.infoTareaResponse;
import com.tareas.api.tareas.persistence.entity.Tarea;
import com.tareas.api.tareas.persistence.entity.Tipo;
import com.tareas.api.tareas.services.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/{username}/por-fecha")
    private ResponseEntity<?> getTareasFecha(@PathVariable String username, @RequestParam("fecha")
    @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate fecha){
        List<Tarea> ts = tareaService.getTateasByDate(username, fecha);
        return ResponseEntity.ok(ts);
    }

    @GetMapping("/{username}/por-realizar")
    private ResponseEntity<?> getTareasRealizar(@PathVariable String username, @RequestParam("realizada") Boolean realizada){
        List<Tarea> ts = tareaService.getTareasByRealizada(username,realizada);
        return ResponseEntity.ok(ts);
    }

    @GetMapping("/info")
    private ResponseEntity<?> getInfoTareas(){
        List<infoTareaResponse> tareasinfo=  tareaService.getInfoTareas();
        return ResponseEntity.ok(tareasinfo);
    }
}
