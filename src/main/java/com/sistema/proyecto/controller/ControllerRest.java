package com.sistema.proyecto.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerRest {
    @GetMapping("/")
    public String home(){
        return "index";
    }
    @GetMapping("/listar")
    public String listar(){
        return "listar";
    }

    @GetMapping("/registrar")
    public String registrar(){
        return "registrar";
    }

    @GetMapping("/inicio")
    public String inicio(){
        return "index";
    }
}

