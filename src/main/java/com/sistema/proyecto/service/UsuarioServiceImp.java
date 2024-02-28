package com.sistema.proyecto.service;

import com.sistema.proyecto.model.Usuario;
import com.sistema.proyecto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service

public class UsuarioServiceImp implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository; //la interfaz que permite acceder a los datos de Usuario en la BD(utilizando los metodos implementados ahi*una libreria se encarga de eso *)

    @Override
    @Transactional(readOnly = true) //esta anotacion indica que es solo de lectura (no guarda, actualiza ni elimina) este metodo
    public List<Usuario> listaUsuarios() {

        return (List<Usuario>) usuarioRepository.findAll() ;
    }

    //este metodo guarda un usuario nuevo o actualiza uno existente
    @Override
    public void guardar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    //busca un auto en la BD por su ID y lo devuelve
    @Override
    public Usuario buscarUsuario(Usuario usuario) {
        return usuarioRepository.findById(usuario.getId()).orElse(null);
    }

}


