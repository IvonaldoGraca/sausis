package com.example.sausis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sausis.model.Estado;
import com.example.sausis.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    
    List<Paciente> findByEstado(Estado estado);
    List<Paciente>findAll();
    Optional<Paciente> findByEmail(String email);
    Optional<Paciente> findByContacto(int contacto);
    
    
}
