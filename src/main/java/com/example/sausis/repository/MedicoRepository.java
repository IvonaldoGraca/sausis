package com.example.sausis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sausis.model.Estado;
import com.example.sausis.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico,Long>{

    List<Medico> findByEstado(Estado estado);

    Optional<Medico> findByEmail(String email);

    Optional<Medico> findByContacto(int contacto);
    
}
