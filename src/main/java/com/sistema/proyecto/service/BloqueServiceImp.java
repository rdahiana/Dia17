package com.sistema.proyecto.service;

import com.sistema.proyecto.model.Bloque;
import com.sistema.proyecto.repository.BloqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BloqueServiceImp implements BloqueService {

    @Autowired
    private BloqueRepository bloqueRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Bloque> listaBloques() {
        return (List<Bloque>) bloqueRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Bloque> buscarBloque(char id_bloque) {
        return bloqueRepository.findById(id_bloque);
    }

    //----------------ESTO YA FORMA PARTE DEL API REST----------------------

    public ArrayList<Bloque> obtenerBloques() {
        return (ArrayList<Bloque>) bloqueRepository.findAll();
    }

    public Optional<Bloque> obtenerPorID(char id_bloque) {return bloqueRepository.findById(id_bloque);
    }

    public Bloque guardarBloque(Bloque bloque) {
        return bloqueRepository.save(bloque);
    }

    public boolean eliminarBloque (char id_bloque){
        try{
            bloqueRepository.deleteById(id_bloque);
            return true;
        }catch(Exception e ){
            return false;
        }
    }

}