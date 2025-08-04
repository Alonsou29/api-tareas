package com.tareas.api.tareas.persistence.repository;

import com.tareas.api.tareas.DTO.infoTareaResponse;
import com.tareas.api.tareas.persistence.entity.Tarea;
import com.tareas.api.tareas.persistence.entity.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TareasRepository extends JpaRepository<Tarea,Integer> {

    List<Tarea> findAllByUsuario_id(int id);
    List<Tarea> findAllByTipo(Tipo tipo);
    List<Tarea> findAllByFechaAndUsuario_id(LocalDate fecha, int id);
    List<Tarea> findAllByRealizadoAndUsuario_id(Boolean realizado,int id);

    @Query(value="select titulo, resumen,realizado,fecha, tp.nombre, u.username from tipo as tp INNER JOIN tarea as t INNER JOIN usuario as u ON t.usuario_id = u.id ON t.tipo_id = t.id order by t.titulo;",nativeQuery = true)
    List<infoTareaResponse> getInfoTareas();
}