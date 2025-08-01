package com.tareas.api.tareas.services;

import com.tareas.api.tareas.persistence.entity.Tipo;

import java.util.List;

public interface TiposService {

    List<Tipo> getAlltipos();
    Tipo addTipo(Tipo tipo);
}
