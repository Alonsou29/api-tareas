package com.tareas.api.tareas.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class infoTareaResponse {
    private String titulo;
    private String resumen;
    private Boolean realizado;
    private Date fecha;
    private String nombre;
    private String username;

}
