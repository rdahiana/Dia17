

package com.sistema.proyecto.repository;

import com.sistema.proyecto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//es el que se encaga de crear la tabla en base a la clase que creamos
//se encarga de conectarse con la bd
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> { //<nombre_tabla, tipoPK>
}
