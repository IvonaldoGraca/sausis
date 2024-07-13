package com.example.sausis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sausis.model.Estado;
import com.example.sausis.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    List<Usuario> findByEstado(Estado ativado);
    
}
