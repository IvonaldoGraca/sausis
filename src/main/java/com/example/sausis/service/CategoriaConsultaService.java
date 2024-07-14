package com.example.sausis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sausis.model.Estado;
import com.example.sausis.model.CategoriaConsulta;
import com.example.sausis.repository.CategoriaConsultaRepository;


import jakarta.persistence.EntityNotFoundException;
@Service

public class CategoriaConsultaService {

    @Autowired CategoriaConsultaRepository categoriaConsultaRepository;

    public List<CategoriaConsulta> findAll() {
        return categoriaConsultaRepository.findAll();
    }

    public Optional<CategoriaConsulta> findByEstado() {
        return categoriaConsultaRepository.findByEstado(Estado.ATIVADO);
    }

    public Optional<CategoriaConsulta> findAllActive() {
        return categoriaConsultaRepository.findByEstado(Estado.ATIVADO);
    }

    public Optional<CategoriaConsulta> findAllADelteded() {
        return categoriaConsultaRepository.findByEstado(Estado.ELIMINADO);
    }

    public Optional<CategoriaConsulta> findAllAPendente() {
        return categoriaConsultaRepository.findByEstado(Estado.PENDENTE);
    }

      public Optional<CategoriaConsulta> findById(Long id) {
        return categoriaConsultaRepository.findById(id);
    }

    public void deleteById(Long id) {
          Optional<CategoriaConsulta> CategoriaConsultaOpt = categoriaConsultaRepository.findById(id);
            if (CategoriaConsultaOpt.isPresent()) {
                CategoriaConsulta CategoriaConsulta = CategoriaConsultaOpt.get();
                categoriaConsultaRepository.save(CategoriaConsulta);
            } else {
                throw new EntityNotFoundException("Categoria de Funcionário com o ID " + id + " não encontrada.");
            }
    }

    public CategoriaConsulta update(Long id, CategoriaConsulta categoriaConsulta) {
            Optional<CategoriaConsulta> existingcategoriaOpt = categoriaConsultaRepository.findById(id);
            if (existingcategoriaOpt.isPresent()) {
                CategoriaConsulta existingcategoria = existingcategoriaOpt.get();
                existingcategoria.setNome(categoriaConsulta.getNome());
                return categoriaConsultaRepository.save(existingcategoria);
            } else {
                throw new EntityNotFoundException("Categoria de consulta não encontrada " + id);
            }
        }

    public CategoriaConsulta save(CategoriaConsulta CategoriaConsulta) {
           Optional<CategoriaConsulta> existingcategoria = categoriaConsultaRepository.findBynome(CategoriaConsulta.getNome());
        if (existingcategoria.isPresent()) {
            throw new IllegalArgumentException("Esta categoria de Consulta já existe.");
        }
        
        return categoriaConsultaRepository.save(CategoriaConsulta);
    }
}
