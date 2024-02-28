package com.sistema.proyecto.service;

import com.sistema.proyecto.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    //metodo para listar todos los usuarios
    public List<Usuario> listaUsuarios();

    //metodo para guardar un usuario
    public void guardar(Usuario usuario);

    public Usuario buscarUsuario(Long ci);
}
