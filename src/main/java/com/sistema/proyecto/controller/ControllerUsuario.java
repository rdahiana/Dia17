package com.sistema.proyecto.controller;

import com.sistema.proyecto.model.Usuario;
import com.sistema.proyecto.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ControllerUsuario {

    private final UsuarioService usuarioService;

    // Constructor injection to inject UsuarioService
    public ControllerUsuario(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        List<Usuario> usuarios = usuarioService.listaUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "listar"; // Reemplaza "nombre_de_tu_html" con el nombre de tu archivo HTML
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
    public String guardar(@ModelAttribute("usuario") Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "registrar"; // Retorna la página de registro si hay errores
        }

        // Guardar el usuario utilizando el servicio de usuario
        usuarioService.guardar(usuario);

        // Agregar un mensaje de éxito al atributo flash para que se muestre en la próxima solicitud
        redirectAttributes.addFlashAttribute("mensaje", "¡Usuario guardado correctamente!");

        // Redirigir a la página de inicio
        return "redirect:/inicio";
    }


    @GetMapping("/buscar")
    public String buscarUsuarioPorCI(@RequestParam(name = "ci", required = false) Long ci, Model model) {
        if (ci == null) {
            // Si el parámetro ci está vacío o no está presente, redirigir a la página de registro
            return "redirect:/registrar";
        } else {
            Usuario usuario = usuarioService.buscarUsuario(ci);
            if (usuario != null) {
                // Si se encontró el usuario, redireccionar a la página correspondiente
                return "redirect:/bloques";
            } else {
                // Si no se encontró el usuario, redireccionar a la página de registro
                return "redirect:/registrar";
            }
        }
    }



}
