package com.example.sausis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sausis.model.Estado;
import com.example.sausis.model.Funcionario;


public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {

    List<Funcionario> findByEstado(Estado estado);

    Optional<Funcionario> findByEmail(String email);

    Optional<Funcionario> findByContacto(int contacto);

List<Funcionario> findAllPendente();

List<Funcionario>findAllDeleted();

    
}
