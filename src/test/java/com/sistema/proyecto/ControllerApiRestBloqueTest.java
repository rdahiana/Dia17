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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.json.JSONArray;
import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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
        // Crear algunos usuarios
        Bloque bloque1 = new Bloque();
        bloque1.setId_bloque('A');
        bloque1.setNombre_wifi("NombreWifiA");
        bloque1.setContraseña("ContraseñaWifiA");

        Bloque bloque2 = new Bloque();
        bloque2.setId_bloque('B');
        bloque2.setNombre_wifi("NombreWifiB");
        bloque2.setContraseña("ContraseñaWifiB");

        // Agregar los usuarios a la lista
        ArrayList<Bloque> bloques = new ArrayList<>();
        bloques.add(bloque1);
        bloques.add(bloque2);
        when(bloqueServiceImp.obtenerBloques()).thenReturn(bloques);

        MvcResult result = mockMvc.perform(get("/rest-bloque"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nombre_wifi").value("NombreWifiA"))
                .andExpect(jsonPath("$[0].contraseña").value("ContraseñaWifiA"))
                .andExpect(jsonPath("$[1].nombre_wifi").value("NombreWifiB"))
                .andExpect(jsonPath("$[1].contraseña").value("ContraseñaWifiB"))
                .andReturn();
        // Extraer el JSON de la respuesta
        String jsonResponse = result.getResponse().getContentAsString();

        // Convertir el JSON a un objeto JSON
        JSONArray jsonArray = new JSONArray(jsonResponse);

        // Verificar el valor de id_bloque para el primer objeto del array
        JSONObject primerObjeto = jsonArray.getJSONObject(0);
        assertEquals("A", primerObjeto.getString("id_bloque"));
    }
    @Test
    public void testObtenerPorID() throws Exception {
        // Definir el ID del bloque de prueba
        char idBloque = 'A';

        // Crear un bloque de prueba
        Bloque bloque = new Bloque();
        bloque.setId_bloque(idBloque);
        bloque.setNombre_wifi("nombre_wifi");
        bloque.setContraseña("contraseña");

        // Configurar el servicio para devolver el bloque de prueba cuando se llama con el ID correspondiente
        when(bloqueServiceImp.obtenerPorID(idBloque)).thenReturn(Optional.of(bloque));

        // Realizar la solicitud HTTP GET al endpoint /rest-bloque/{id}
        MvcResult result = mockMvc.perform(get("/rest-bloque/{id}", idBloque))
                .andExpect(status().isOk()) // Verificar que la respuesta es 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Verificar que el tipo de contenido es JSON
                // Verificar que el JSON devuelto contiene los datos esperados del bloque
                .andExpect(jsonPath("$.nombre_wifi").value("nombre_wifi"))
                .andExpect(jsonPath("$.contraseña").value("contraseña"))
                .andReturn();
        // Extraer el JSON de la respuesta
        String jsonResponse = result.getResponse().getContentAsString();

        // Verificar el valor de id_bloque para el primer objeto del array
        JSONObject primerObjeto = new JSONObject(jsonResponse);
        assertEquals("A", primerObjeto.getString("id_bloque"));;

        // Verificar que se llamó al método obtenerPorID() del servicio una vez con el ID correspondiente y no se realizaron más interacciones
        verify(bloqueServiceImp, times(1)).obtenerPorID(idBloque);
        verifyNoMoreInteractions(bloqueServiceImp);
    }

    @Test
    public void testGuardarBloque() throws Exception {
        // Crear un bloque de prueba para guardar
        Bloque bloque = new Bloque();
        bloque.setId_bloque('A');
        bloque.setNombre_wifi("wifiA");
        bloque.setContraseña("123456");

        // Configurar el servicio para devolver el bloque de prueba cuando se llama con cualquier bloque
        when(bloqueServiceImp.guardarBloque(any(Bloque.class))).thenReturn(bloque);

        // Realizar una solicitud HTTP POST al endpoint /rest-bloque/save con el bloque de prueba en formato JSON
        mockMvc.perform(post("/rest-bloque/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id_bloque\":\"A\", \"nombre_wifi\":\"wifiA\", \"contraseña\":\"123456\"}"))
                .andExpect(status().isOk()) // Verificar que la respuesta es 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)); // Verificar que el tipo de contenido es JSON

        // Verificar que se llamó al método guardarBloque() del servicio una vez con cualquier bloque y no se realizaron más interacciones
        verify(bloqueServiceImp, times(1)).guardarBloque(any(Bloque.class));
        verifyNoMoreInteractions(bloqueServiceImp);
    }

    @Test
    public void testEliminarBloque() throws Exception {
        // Definir el ID del bloque que se va a eliminar
        char idBloque = 'A';

        // Configurar el servicio para devolver true cuando se llama al método eliminarBloque() con el ID correspondiente
        when(bloqueServiceImp.eliminarBloque(idBloque)).thenReturn(true);

        // Realizar una solicitud HTTP DELETE al endpoint /rest-bloque/{id} con el ID del bloque que se va a eliminar
        mockMvc.perform(delete("/rest-bloque/{id}", idBloque))
                .andExpect(status().isOk()) // Verificar que la respuesta es 200 OK
                .andExpect(content().string("Se elimino el bloque  " + idBloque)); // Verificar que el cuerpo de la respuesta es el mensaje esperado

        // Verificar que se llamó al método eliminarBloque() del servicio una vez con el ID correspondiente y no se realizaron más interacciones
        verify(bloqueServiceImp, times(1)).eliminarBloque(idBloque);
        verifyNoMoreInteractions(bloqueServiceImp);
    }

}
