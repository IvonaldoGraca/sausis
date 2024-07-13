package com.example.sausis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sausis.model.CategoriaFuncionario;
import com.example.sausis.model.Estado;

public interface CategoriaFuncionarioRepository extends JpaRepository<CategoriaFuncionario,Long>{

    Optional<CategoriaFuncionario> findByEstado(Estado estado);

    Optional<CategoriaFuncionario> findBynome(String nome);

    
    
}
