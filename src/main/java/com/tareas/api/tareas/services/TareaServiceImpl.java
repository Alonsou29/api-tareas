package com.tareas.api.tareas.services;

import com.tareas.api.tareas.persistence.entity.Tarea;
import com.tareas.api.tareas.persistence.entity.Tipo;
import com.tareas.api.tareas.persistence.entity.Usuario;
import com.tareas.api.tareas.persistence.repository.TareasRepository;
import com.tareas.api.tareas.persistence.repository.TiposRepository;
import com.tareas.api.tareas.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareasRepository tareasRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TiposRepository tiposRepository;

    @Override
    public Tarea addTarea(String username, String tipo, Tarea tarea) {
        try{
            Usuario usu = usuarioRepository.getUsuarioByUsername(username);
            Tipo tp = tiposRepository.findByNombre(tipo);

            LocalDate today = LocalDate.now();
            tarea.setFecha(today);
            tarea.setRealizado(false);
            tarea.setUsuario(usu);
            tarea.setTipo(tp);
            return tareasRepository.save(tarea);
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public Tarea updateTarea(Tarea tarea) {
        try{
            Tarea tareaUp = tareasRepository.findById(tarea.getId()).orElse(null);
            if(tareaUp != null){
                tareaUp.setResumen(tarea.getResumen());
                tareaUp.setTitulo(tarea.getTitulo());
                tareaUp.setFecha(tarea.getFecha());
                tareaUp.setRealizado(tarea.isRealizado());
                return tareasRepository.save(tareaUp);
            }else{
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Tarea deleteTarea(int id) {
        Tarea tareaUp = tareasRepository.findById(id).orElse(null);
        if(tareaUp != null){
            tareasRepository.delete(tareaUp);
            return tareaUp;
        }
        return null;
    }

    @Override
    public List<Tarea> deleteTareaByNombre(Tipo tipo) {
        List<Tarea> tareas = tareasRepository.findAllByTipo(tipo);
        tareasRepository.deleteAll(tareas);
        return tareas;
    }

    @Override
    public List<Tarea> getTareasByUsername(String user) {
        Usuario usu = usuarioRepository.getUsuarioByUsername(user);
        return tareasRepository.findAllByUsuario_id(usu.getId());
    }
}
