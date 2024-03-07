package com.sistema.proyecto.controller;
import com.sistema.proyecto.model.Bloque;
import com.sistema.proyecto.service.BloqueServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/rest-bloque")

public class ControllerApiRestBloque {

    //----------------METODOS DEL API REST-------------------------

    @Autowired
    BloqueServiceImp bloqueServiceImp;
    public ControllerApiRestBloque (BloqueServiceImp bloqueServiceImp) {
        this.bloqueServiceImp = bloqueServiceImp;
    }

    @GetMapping()
    public ArrayList<Bloque> obtenerBloques() {
        return (ArrayList<Bloque>) bloqueServiceImp.obtenerBloques();
    }

    @GetMapping( path = "/{id}")
    public Optional<Bloque> obtenerPorID(@PathVariable("id") char id_bloque){
        return this.bloqueServiceImp.obtenerPorID(id_bloque);
    }

    @PostMapping("/save")
    public Bloque guardarBloque(@RequestBody Bloque bloque){
        return this.bloqueServiceImp.guardarBloque(bloque);
    }
    @DeleteMapping(path = "{id}")
    public String eliminarBloque (@PathVariable("id") char id_bloque ){
        boolean ok = this.bloqueServiceImp.eliminarBloque(id_bloque);
        if (ok){
            return "Se elimino el bloque  " +id_bloque;
        }else{
            return "No se pudo realizar la eliminacion del bloque  "+id_bloque;
        }
    }


}
