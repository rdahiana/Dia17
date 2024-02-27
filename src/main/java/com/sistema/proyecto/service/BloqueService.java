package com.sistema.proyecto.service;

import com.sistema.proyecto.model.Bloque;
import com.sistema.proyecto.model.Usuario;
import com.sistema.proyecto.repository.BloqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class  BloqueService {
    @Autowired
    private BloqueRepository bloqueRepository;


    public List<Bloque> list(){
        return bloqueRepository.findAll();
    }


    public Optional<Bloque> findById(char id_bloque){
        return bloqueRepository.findById(id_bloque);
    }
}


