package com.example.sausis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sausis.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {
    
}
