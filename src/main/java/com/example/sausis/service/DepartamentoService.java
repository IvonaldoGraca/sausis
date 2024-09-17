package com.example.sausis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sausis.model.Departamento;
import com.example.sausis.model.Estado;
import com.example.sausis.repository.DepartamentoRepository;
import jakarta.persistence.EntityNotFoundException;
@Service

public class DepartamentoService {

    @Autowired DepartamentoRepository departamentoRepository;

    public List<Departamento> findAll() {
        return departamentoRepository.findAll();
    }
    
    public Optional<Departamento> findByEstado() {
        return departamentoRepository.findByEstado(Estado.ATIVADO);
    }

    public Optional<Departamento> findAllActive() {
        return departamentoRepository.findByEstado(Estado.ATIVADO);
    }

    public Optional<Departamento> findAllADelteded() {
        return departamentoRepository.findByEstado(Estado.ELIMINADO);
    }


    public Departamento save(Departamento Departamento) {
        Optional<Departamento> existingDepartamento = departamentoRepository.findBynome(Departamento.getNome());
        if (existingDepartamento.isPresent()) {
            throw new IllegalArgumentException("Este Departamento já existe.");
        }
        return departamentoRepository.save(Departamento);
    }

    
        public void deleteById(Long id) {
            Optional<Departamento> DepartamentoOpt = departamentoRepository.findById(id);
            if (DepartamentoOpt.isPresent()) {
                Departamento departamento = DepartamentoOpt.get();
                 departamento.setEstado(Estado.ELIMINADO);
                departamentoRepository.save(departamento);
            } else {
                throw new EntityNotFoundException("Departamento com o ID " + id + " não encontrado.");
            }
        }
       
        public Departamento update(Long id, Departamento Departamento) {
            Optional<Departamento> existingDepartamentoOpt = departamentoRepository.findById(id);
            if (existingDepartamentoOpt.isPresent()) {
                Departamento existingDepartamento = existingDepartamentoOpt.get();
                existingDepartamento.setNome(Departamento.getNome());
                return departamentoRepository.save(existingDepartamento);
            } else {
                throw new EntityNotFoundException("Departamento não encontrado " + id);
            }
        }

    public Optional<Departamento> findById(Long id) {
        return departamentoRepository.findById(id);
    }

}
