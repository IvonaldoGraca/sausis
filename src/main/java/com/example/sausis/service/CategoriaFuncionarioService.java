package com.example.sausis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sausis.model.Estado;
import com.example.sausis.model.CategoriaFuncionario;
import com.example.sausis.repository.CategoriaFuncionarioRepository;


import jakarta.persistence.EntityNotFoundException;
@Service

public class CategoriaFuncionarioService {

    @Autowired CategoriaFuncionarioRepository categoriaFuncionarioRepository;

    public List<CategoriaFuncionario> findAll() {
        return categoriaFuncionarioRepository.findAll();
    }

    public Optional<CategoriaFuncionario> findByEstado() {
        return categoriaFuncionarioRepository.findByEstado(Estado.ATIVADO);
    }

    public Optional<CategoriaFuncionario> findAllActive() {
        return categoriaFuncionarioRepository.findByEstado(Estado.ATIVADO);
    }

    public Optional<CategoriaFuncionario> findAllADelteded() {
        return categoriaFuncionarioRepository.findByEstado(Estado.ELIMINADO);
    }

    public Optional<CategoriaFuncionario> findAllAPendente() {
        return categoriaFuncionarioRepository.findByEstado(Estado.PENDENTE);
    }

      public Optional<CategoriaFuncionario> findById(Long id) {
        return categoriaFuncionarioRepository.findById(id);
    }

    public void deleteById(Long id) {
          Optional<CategoriaFuncionario> CategoriaFuncionarioOpt = categoriaFuncionarioRepository.findById(id);
            if (CategoriaFuncionarioOpt.isPresent()) {
                CategoriaFuncionario CategoriaFuncionario = CategoriaFuncionarioOpt.get();
                categoriaFuncionarioRepository.save(CategoriaFuncionario);
            } else {
                throw new EntityNotFoundException("Categoria de Funcionário com o ID " + id + " não encontrada.");
            }
    }

    public CategoriaFuncionario update(Long id, CategoriaFuncionario categoriaFuncionario) {
            Optional<CategoriaFuncionario> existingcategoriaOpt = categoriaFuncionarioRepository.findById(id);
            if (existingcategoriaOpt.isPresent()) {
                CategoriaFuncionario existingcategoria = existingcategoriaOpt.get();
                existingcategoria.setNome(categoriaFuncionario.getNome());
                return categoriaFuncionarioRepository.save(existingcategoria);
            } else {
                throw new EntityNotFoundException("CategoriaFuncionario não encontrado " + id);
            }
        }

    public CategoriaFuncionario save(CategoriaFuncionario categoriaFuncionario) {
           Optional<CategoriaFuncionario> existingcategoria = categoriaFuncionarioRepository.findBynome(categoriaFuncionario.getNome());
        if (existingcategoria.isPresent()) {
            throw new IllegalArgumentException("Esta categoria de funcionário já existe.");
        }
        
        return categoriaFuncionarioRepository.save(categoriaFuncionario);
    }
}
