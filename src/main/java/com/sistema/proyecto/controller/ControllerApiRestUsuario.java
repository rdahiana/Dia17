package com.sistema.proyecto.controller;

import com.sistema.proyecto.model.Usuario;
import com.sistema.proyecto.service.UsuarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/rest-usuario")

public class ControllerApiRestUsuario {

    //----------------METODOS DEL API REST-------------------------

    @Autowired
    UsuarioServiceImp usuarioServiceImp;
    public ControllerApiRestUsuario (UsuarioServiceImp usuarioServiceImp) {
        this.usuarioServiceImp = usuarioServiceImp;
    }

    @GetMapping()
    public ArrayList<Usuario> obtenerUsuarios() {
        return (ArrayList<Usuario>) usuarioServiceImp.obtenerUsuarios();
    }

    @GetMapping( path = "/{ci}")
    public Optional<Usuario> obtenerPorCi(@PathVariable("ci") Long ci){
        return this.usuarioServiceImp.obtenerPorCi(ci);
    }
    @PostMapping("/save")
    public Usuario guardarUsuario(@RequestBody Usuario usuario){
        return this.usuarioServiceImp.guardarUsuario(usuario);
    }

    @DeleteMapping(path = "{ci}")
    public String eliminarUsuario (@PathVariable("ci") Long ci ){
        boolean ok = this.usuarioServiceImp.eliminarUsuario(ci);
        if (ok){
            return "Se elimino el usuario con ci " +ci;
        }else{
            return "No se pudo realizar la eliminacion del usuario con ci "+ci;
        }
    }

}
