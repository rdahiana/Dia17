package com.sistema.proyecto.controller;

import com.sistema.proyecto.model.Bloque;
import com.sistema.proyecto.service.BloqueService; // Se agregó la importación de BloqueService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; // Se agregó la importación de PathVariable

import java.util.List;
import java.util.Optional;

@Controller
public class ControllerBloque {

    private final BloqueService bloqueService;

    @Autowired // Se agregó la anotación @Autowired para la inyección de dependencias
    public ControllerBloque(BloqueService bloqueService) {
        this.bloqueService = bloqueService;
    }

    @GetMapping("/bloques")
    public String listar(Model model) {
        List<Bloque> bloques = bloqueService.listaBloques();
        model.addAttribute("bloques", bloques);
        return "bloques";
    }

    @GetMapping("/bloque/{id}")
    public String obtenerBloquePorId(@PathVariable char id, Model model) { // Se cambió el nombre de la variable a 'id'
        Optional<Bloque> bloque = bloqueService.buscarBloque(id);
        model.addAttribute("bloque", bloque.orElse(null));
        return "bloques";
    }
}
