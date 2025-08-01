package com.tareas.api.tareas.services;

import com.tareas.api.tareas.persistence.entity.Tarea;
import com.tareas.api.tareas.persistence.entity.Tipo;
import com.tareas.api.tareas.persistence.repository.TareasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareasRepository tareasRepository;

    @Override
    public List<Tarea> deleteTareaByUserId(int id) {
        List<Tarea> tareas = tareasRepository.findAllByUsuario_id(id);
        tareasRepository.deleteAll(tareas);
        return tareas;
    }

    @Override
    public List<Tarea> deleteTareaByNombre(Tipo tipo) {
        List<Tarea> tareas = tareasRepository.findAllByTipo(tipo);
        tareasRepository.deleteAll(tareas);
        return tareas;
    }
}
