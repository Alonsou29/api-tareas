package com.tareas.api.tareas.services;

import com.tareas.api.tareas.persistence.entity.Tarea;
import com.tareas.api.tareas.persistence.entity.Tipo;

import java.util.List;

public interface TareaService {

    Tarea addTarea(String username, String nombre, Tarea tarea);
    Tarea updateTarea(Tarea tarea);
    Tarea deleteTarea(int id);
    List<Tarea> deleteTareaByNombre(Tipo tipo);
    List<Tarea> getTareasByUsername(String username);


}
