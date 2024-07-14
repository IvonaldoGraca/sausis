package com.example.sausis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sausis.model.CategoriaConsulta;
import com.example.sausis.model.Estado;

public interface CategoriaConsultaRepository extends JpaRepository<CategoriaConsulta,Long> {

     Optional<CategoriaConsulta> findByEstado(Estado estado);

    Optional<CategoriaConsulta> findBynome(String nome);
}
