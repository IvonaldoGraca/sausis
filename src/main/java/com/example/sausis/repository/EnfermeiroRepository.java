package com.example.sausis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sausis.model.Enfermeiro;
import com.example.sausis.model.Estado;

public interface EnfermeiroRepository extends JpaRepository<Enfermeiro,Long> {

    List<Enfermeiro> findByEstado(Estado ativado);

    Optional<Enfermeiro> findByEmail(String email);

    Optional<Enfermeiro> findByContacto(int contacto);
    
}
