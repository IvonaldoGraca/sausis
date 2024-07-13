package com.example.sausis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sausis.model.Estado;
import com.example.sausis.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    List<Usuario> findByEstado(Estado ativado);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByContacto(int contacto);
    
}
