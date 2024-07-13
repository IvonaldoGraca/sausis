package com.example.sausis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sausis.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico,Long>{
    
}
