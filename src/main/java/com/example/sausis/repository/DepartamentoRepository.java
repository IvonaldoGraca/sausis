package com.example.sausis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sausis.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento,Long>{
    
}
