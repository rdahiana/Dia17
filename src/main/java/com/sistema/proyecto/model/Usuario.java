package com.sistema.proyecto.model;


import jakarta.persistence.Entity; //el que le dice que va ser una tabla
import jakarta.persistence.Id;
import lombok.Data; //generar los set y gets

@Data
@Entity
public class Usuario {
    @Id
    private Long ci ;
    private String nombre;
    private String apellido;
    private String carrera;
    private String semestre;

}
