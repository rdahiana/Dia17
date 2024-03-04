package com.sistema.proyecto.service;

import com.sistema.proyecto.model.Bloque;

import java.util.List;
import java.util.Optional;

public interface BloqueService {

    //metodo para listar todos los bloques
    public List<Bloque> listaBloques();

    public Optional<Bloque> buscarBloque(char id_bloque);
}
