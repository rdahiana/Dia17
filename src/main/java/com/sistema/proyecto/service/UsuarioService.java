package com.sistema.proyecto.service;

import com.sistema.proyecto.model.Usuario;
import com.sistema.proyecto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    //trae todas las filas de la tabla
    public List<Usuario> list(){
        return usuarioRepository.findAll();
    }

    //trae las filas de la tabla que le corresponden a ci

    public Optional<Usuario> findById(Long ci){
        return usuarioRepository.findById(ci);
    }
}


