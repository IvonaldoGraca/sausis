package com.example.sausis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sausis.model.Enfermeiro;

public interface EnfermeiroRepository extends JpaRepository<Enfermeiro,Long> {
    
}
