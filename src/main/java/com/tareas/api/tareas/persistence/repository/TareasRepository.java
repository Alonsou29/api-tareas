package com.tareas.api.tareas.persistence.repository;

import com.tareas.api.tareas.persistence.entity.Tarea;
import com.tareas.api.tareas.persistence.entity.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TareasRepository extends JpaRepository<Tarea,Integer> {

    List<Tarea> findAllByUsuario_id(int id);
    List<Tarea> findAllByTipo(Tipo tipo);
}
