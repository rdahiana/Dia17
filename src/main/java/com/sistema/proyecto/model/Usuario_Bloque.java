package com.sistema.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@IdClass(Usuario_Bloque.UsuarioBloqueId.class)
public class Usuario_Bloque {

    @Data
    public static class UsuarioBloqueId implements Serializable {
        private Long usuario;
        private char bloque;
    }

    @Id
    @ManyToOne
    private Usuario usuario;

    @Id
    @ManyToOne
    private Bloque bloque;

}
