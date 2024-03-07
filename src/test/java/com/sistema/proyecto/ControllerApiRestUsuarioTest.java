package com.sistema.proyecto;

import com.sistema.proyecto.controller.ControllerApiRestUsuario;
import com.sistema.proyecto.model.Usuario;
import com.sistema.proyecto.service.UsuarioServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ControllerApiRestUsuarioTest {

    private MockMvc mockMvc;

    @Mock
    private UsuarioServiceImp usuarioServiceImp;

    @InjectMocks
    private ControllerApiRestUsuario controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testObtenerUsuarios() throws Exception {
        // Crear algunos usuarios
        Usuario usuario1 = new Usuario();
        usuario1.setCi(123456789L);
        usuario1.setNombre("Nombre1");
        usuario1.setApellido("Apellido1");
        usuario1.setCarrera("Carrera1");
        usuario1.setSemestre("Semestre1");

        Usuario usuario2 = new Usuario();
        usuario2.setCi(987654321L);
        usuario2.setNombre("Nombre2");
        usuario2.setApellido("Apellido2");
        usuario2.setCarrera("Carrera2");
        usuario2.setSemestre("Semestre2");

        // Agregar los usuarios a la lista
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario1);
        usuarios.add(usuario2);
        when(usuarioServiceImp.obtenerUsuarios()).thenReturn(usuarios);

        mockMvc.perform(get("/rest-usuario"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].ci").value(123456789))
                .andExpect(jsonPath("$[0].nombre").value("Nombre1"))
                .andExpect(jsonPath("$[0].apellido").value("Apellido1"))
                .andExpect(jsonPath("$[0].carrera").value("Carrera1"))
                .andExpect(jsonPath("$[0].semestre").value("Semestre1"))
                .andExpect(jsonPath("$[1].ci").value(987654321))
                .andExpect(jsonPath("$[1].nombre").value("Nombre2"))
                .andExpect(jsonPath("$[1].apellido").value("Apellido2"))
                .andExpect(jsonPath("$[1].carrera").value("Carrera2"))
                .andExpect(jsonPath("$[1].semestre").value("Semestre2"));
    }

    @Test
    public void testObtenerPorCi() throws Exception {
        Long ci = 123456789L;
        Usuario usuario = new Usuario();
        usuario.setCi(ci);
        usuario.setNombre("Nombre");
        usuario.setApellido("Apellido");
        usuario.setCarrera("Carrera");
        usuario.setSemestre("Semestre");

        when(usuarioServiceImp.obtenerPorCi(ci)).thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/rest-usuario/{ci}", ci))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.ci").value(123456789))
                .andExpect(jsonPath("$.nombre").value("Nombre"))
                .andExpect(jsonPath("$.apellido").value("Apellido"))
                .andExpect(jsonPath("$.carrera").value("Carrera"))
                .andExpect(jsonPath("$.semestre").value("Semestre"));
    }

    @Test
    public void testGuardarUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setCi(123456789L);
        usuario.setNombre("Nombre");
        usuario.setApellido("Apellido");
        usuario.setCarrera("Carrera");
        usuario.setSemestre("Semestre");

        when(usuarioServiceImp.guardarUsuario(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/rest-usuario/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"ci\":123456789, \"nombre\":\"Nombre\", \"apellido\":\"Apellido\", \"carrera\":\"Carrera\", \"semestre\":\"Semestre\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.ci").value(123456789))
                .andExpect(jsonPath("$.nombre").value("Nombre"))
                .andExpect(jsonPath("$.apellido").value("Apellido"))
                .andExpect(jsonPath("$.carrera").value("Carrera"))
                .andExpect(jsonPath("$.semestre").value("Semestre"));
    }

    @Test
    public void testEliminarUsuario() throws Exception {
        Long ci = 123456789L;

        when(usuarioServiceImp.eliminarUsuario(ci)).thenReturn(true);

        mockMvc.perform(delete("/rest-usuario/{ci}", ci))
                .andExpect(status().isOk())
                .andExpect(content().string("Se elimino el usuario con ci " + ci));
    }
}
