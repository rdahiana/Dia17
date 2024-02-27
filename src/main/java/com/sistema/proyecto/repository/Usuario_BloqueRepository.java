package com.sistema.proyecto.repository;
import com.sistema.proyecto.model.Usuario_Bloque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Usuario_BloqueRepository extends JpaRepository<Usuario_Bloque, Usuario_Bloque.UsuarioBloqueId> {
}
