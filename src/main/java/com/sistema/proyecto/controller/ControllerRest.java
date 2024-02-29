package com.sistema.proyecto.controller;

import com.sistema.proyecto.model.Usuario;
import com.sistema.proyecto.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class ControllerRest {

    private final UsuarioService usuarioService;

    // Constructor injection to inject UsuarioService
    public ControllerRest(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/listar")
    public String listar(){
        return "listar";
    }

    @GetMapping("/inicio")
    public String inicio(){
        return "index";
    }

    //maneja la solicitud de mostrar el formulario para agregar un nuevo usuario

    @GetMapping("/registrar")
    public String registrar(Model model){
        Usuario usuario = new Usuario();
        //Entonces, esta línea de código está diciendo que queremos hacer disponible el
        // objeto usuario en la vista con el nombre "usuario", de modo que la vista pueda
        // acceder a sus datos y mostrarlos adecuadamente.
        model.addAttribute("usuario", usuario);
        return "registrar";
    }

    //esto gestiona la accion del formulario *especificamos en el html la accion
    // a la cual estaria asociada(th:action)*

    @PostMapping("/guardar")
    public String guardar(@Valid Usuario usuario, Errors error){ //valid es para que Spring se ocupe de validar automaticamente los datos del objeto (de acuerdo a las reglas en la clase del objeto)
        if(error.hasErrors()){ //si hay errores devuelve nuevamente para que se corrija los errores
            return "/registrar";
        }
        usuarioService.guardar(usuario);
        return "/registrar";
    }
}
