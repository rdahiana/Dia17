package com.sistema.proyecto.model;


import jakarta.persistence.Entity; //el que le dice que va ser una tabla
import jakarta.persistence.Id;
import lombok.Data; //generar los set y gets

@Data
@Entity
public class Bloque {
    @Id
    private char id_bloque ;
    private String nombre_wifi;
    private String contraseña;
}
