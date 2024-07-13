package com.example.sausis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sausis.model.Estado;
import com.example.sausis.model.Enfermeiro;
import com.example.sausis.repository.EnfermeiroRepository;
import jakarta.persistence.EntityNotFoundException;
@Service

public class EnfermeiroService {

    @Autowired EnfermeiroRepository EnfermeiroRepository;

    public List<Enfermeiro> findAll() {
        return EnfermeiroRepository.findAll();
    }

    public List<Enfermeiro> findByEstado() {
        return EnfermeiroRepository.findByEstado(Estado.ATIVADO);
    }

    public List<Enfermeiro> findAllActive() {
        return EnfermeiroRepository.findByEstado(Estado.ATIVADO);
    }

    public List<Enfermeiro> findAllADelteded() {
        return EnfermeiroRepository.findByEstado(Estado.ELIMINADO);
    }

    public List<Enfermeiro> findAllAPendente() {
        return EnfermeiroRepository.findByEstado(Estado.PENDENTE);
    }


    public Enfermeiro save(Enfermeiro Enfermeiro) {
        Optional<Enfermeiro> existingEnfermeiro = EnfermeiroRepository.findByEmail(Enfermeiro.getEmail());
        if (existingEnfermeiro.isPresent()) {
            throw new IllegalArgumentException("Este email já está registado, Esqueceu a senha?.");
        }
        Optional<Enfermeiro> existingEnfermeiroByContacto = EnfermeiroRepository.findByContacto(Enfermeiro.getContacto());
        if (existingEnfermeiroByContacto.isPresent()) {
            throw new IllegalArgumentException("Este contacto já está registado.");
        }
        return EnfermeiroRepository.save(Enfermeiro);
    }

    
        public void deleteById(Long id) {
            Optional<Enfermeiro> EnfermeiroOpt = EnfermeiroRepository.findById(id);
            if (EnfermeiroOpt.isPresent()) {
                Enfermeiro Enfermeiro = EnfermeiroOpt.get();
                Enfermeiro.setEstado(Estado.ELIMINADO); 
                EnfermeiroRepository.save(Enfermeiro);
            } else {
                throw new EntityNotFoundException("Usuário com o ID " + id + " não encontrado.");
            }
        }
       
        public Enfermeiro update(Long id, Enfermeiro Enfermeiro) {
            Optional<Enfermeiro> existingEnfermeiroOpt = EnfermeiroRepository.findById(id);
            if (existingEnfermeiroOpt.isPresent()) {
                Enfermeiro existingEnfermeiro = existingEnfermeiroOpt.get();
                // Atualizar os campos do usuário existente com os novos valores
                existingEnfermeiro.setNome(Enfermeiro.getNome());
                existingEnfermeiro.setEmail(Enfermeiro.getEmail());
                existingEnfermeiro.setSenha(Enfermeiro.getSenha());
                existingEnfermeiro.setContacto(Enfermeiro.getContacto());
                existingEnfermeiro.setData_nasc(Enfermeiro.getData_nasc());
                existingEnfermeiro.setEnfnNumOrdem(Enfermeiro.getEnfnNumOrdem());
                // Atualize outros campos conforme necessário
    
                return EnfermeiroRepository.save(existingEnfermeiro);
            } else {
                throw new EntityNotFoundException("Enfermeiro não encontrado " + id);
            }
        }

    public Optional<Enfermeiro> findById(Long id) {
        return EnfermeiroRepository.findById(id)
                .filter(Enfermeiro -> Enfermeiro.getEstado().equals(Estado.ATIVADO));
    }

}
