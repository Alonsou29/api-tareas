package com.tareas.api.tareas.services;

import com.tareas.api.tareas.persistence.entity.Tipo;
import com.tareas.api.tareas.persistence.repository.TiposRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TiposServiceImpl implements TiposService {

    @Autowired
    private TiposRepository tiposRepository;

    @Autowired
    private TareaService tareaService;

    public List<Tipo> getAlltipos() {return tiposRepository.findAll();}

    public Tipo addTipo(Tipo tipo) {
        return tiposRepository.save(tipo);
    }

    public Tipo deleteTipo(String nombre) {
        Tipo tipo = tiposRepository.findByNombre(nombre);
        tareaService.deleteTareaByNombre(tipo);
        tiposRepository.delete(tipo);
        return tipo;
    }

}
