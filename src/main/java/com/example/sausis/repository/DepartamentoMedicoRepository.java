package com.example.sausis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sausis.model.DepartamentoMedico;

public interface DepartamentoMedicoRepository extends JpaRepository<DepartamentoMedico,Long> {
    
}
