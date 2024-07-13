package com.example.sausis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sausis.model.Departamento;
import com.example.sausis.model.Estado;

public interface DepartamentoRepository extends JpaRepository<Departamento,Long>{

    Optional<Departamento> findByEstado(Estado estado);

    Optional<Departamento> findBynome(String nome);
    
}
