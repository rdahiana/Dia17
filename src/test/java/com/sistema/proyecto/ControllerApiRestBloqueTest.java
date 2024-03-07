package com.sistema.proyecto;

import com.sistema.proyecto.controller.ControllerApiRestBloque;
import com.sistema.proyecto.model.Bloque;
import com.sistema.proyecto.service.BloqueServiceImp;
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
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ControllerApiRestBloqueTest {

    private MockMvc mockMvc;

    @Mock
    private BloqueServiceImp bloqueServiceImp;

    @InjectMocks
    private ControllerApiRestBloque controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testObtenerBloques() throws Exception {


        Bloque bloque1 = new Bloque();
        bloque1.setId_bloque('A');
        bloque1.setNombre_wifi("Wifi1");
        bloque1.setContraseña("contraseña1");


        Bloque bloque2 = new Bloque();
        bloque2.setId_bloque('B');
        bloque2.setNombre_wifi("Wifi2");
        bloque2.setContraseña("contraseña2");

        // Agregar los bloques a la lista
        ArrayList<Bloque> bloques = new ArrayList<>();
        bloques.add(bloque1);
        bloques.add(bloque2);
        when(bloqueServiceImp.obtenerBloques()).thenReturn(bloques);

        mockMvc.perform(get("/rest-bloque"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id_bloque").value('A'))
                .andExpect(jsonPath("$[0].Nombre_wifi").value("Wifi1"))
                .andExpect(jsonPath("$[0].Contraseña").value("contraseña1"))

                .andExpect(jsonPath("$[1].id_bloque").value("B"))
                .andExpect(jsonPath("$[1].Nombre_wifi").value("Wifi2"))
                .andExpect(jsonPath("$[1].Contraseña").value("contraseña2"));
        verify(bloqueServiceImp, times(1)).obtenerBloques();
        verifyNoMoreInteractions(bloqueServiceImp);
    }

    @Test
    public void testObtenerPorID() throws Exception {
        // Definir el ID del bloque
        char idBloque = 'A';

        // Crear un bloque con datos necesarios
        Bloque bloque = new Bloque();
        bloque.setId_bloque(idBloque);
        bloque.setNombre_wifi("nombre_wifi");
        bloque.setContraseña("contraseña");

        // Configurar el comportamiento del servicio bloqueServiceImp
        when(bloqueServiceImp.obtenerPorID(idBloque)).thenReturn(Optional.of(bloque));

        mockMvc.perform(get("/rest-bloque/{id}", idBloque))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Verificar que el JSON devuelto contiene los datos esperados del bloque
                .andExpect(jsonPath("$.id_bloque").value('A'))
                .andExpect(jsonPath("$.Nombre_wifi").value("nombre_wifi"))
                .andExpect(jsonPath("$.Contraseña").value("contraseña"));
    }



    @Test
    public void testGuardarBloque() throws Exception {
        // Crear un bloque con datos necesarios para guardar
        Bloque bloque = new Bloque();
        bloque.setId_bloque('A');
        bloque.setNombre_wifi("wifiA");
        bloque.setContraseña("123456");

        // Configurar el comportamiento del servicio bloqueServiceImp
        when(bloqueServiceImp.guardarBloque(any(Bloque.class))).thenReturn(bloque);

        // Realizar una solicitud HTTP POST al endpoint /rest-bloque/save con el bloque en formato JSON
        mockMvc.perform(post("/rest-bloque/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id_bloque\":\"A\", \"nombre_wifi\":\"wifiA\", \"contraseña\":\"123456\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    public void testEliminarBloque() throws Exception {
        // Definir el ID del bloque que se va a eliminar
        char idBloque = 'A';

        // Configurar el comportamiento del servicio bloqueServiceImp
        when(bloqueServiceImp.eliminarBloque(idBloque)).thenReturn(true);

        // Realizar una solicitud HTTP DELETE al endpoint /rest-bloque/{id} con el ID del bloque que se va a eliminar
        mockMvc.perform(delete("/rest-bloque/{id}", idBloque))
                .andExpect(status().isOk())
                .andExpect(content().string("Se elimino el bloque  " + idBloque));

        // Verificar que se llamó al método eliminarBloque() del servicio con el ID correcto
        verify(bloqueServiceImp, times(1)).eliminarBloque(idBloque);
        verifyNoMoreInteractions(bloqueServiceImp);
    }

}
