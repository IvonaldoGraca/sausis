package com.example.sausis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sausis.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    
}
