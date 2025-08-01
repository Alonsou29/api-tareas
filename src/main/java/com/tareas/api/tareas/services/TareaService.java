package com.tareas.api.tareas.services;

import com.tareas.api.tareas.persistence.entity.Tarea;
import com.tareas.api.tareas.persistence.entity.Tipo;

import java.util.List;

public interface TareaService {

    List<Tarea> deleteTareaByUserId(int id);

    List<Tarea> deleteTareaByNombre(Tipo tipo);

}
