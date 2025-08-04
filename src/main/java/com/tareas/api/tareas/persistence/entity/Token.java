package com.tareas.api.tareas.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String token;
    public boolean revoked;
    public boolean expired;
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;
}
