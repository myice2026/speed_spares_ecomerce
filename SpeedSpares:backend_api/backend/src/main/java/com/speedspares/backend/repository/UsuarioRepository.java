package com.speedspares.backend.repository;

import com.speedspares.backend.model.Usuario; // ¡Importa el nuevo nombre!
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// JpaRepository<[Modelo], [Tipo de ID]>
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // ...
}