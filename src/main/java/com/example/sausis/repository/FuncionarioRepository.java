package com.example.sausis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sausis.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
    
}
