package com.example.sausis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sausis.model.Estado;
import com.example.sausis.model.Medico;
import com.example.sausis.repository.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
@Service

public class MedicoService {

    @Autowired MedicoRepository MedicoRepository;

    public List<Medico> findAll() {
        return MedicoRepository.findAll();
    }

    public List<Medico> findAllActive() {
        return MedicoRepository.findByEstado(Estado.ATIVADO);
    }

    public List<Medico> findAllADelteded() {
        return MedicoRepository.findByEstado(Estado.ELIMINADO);
    }

    public List<Medico> findAllAPendente() {
        return MedicoRepository.findByEstado(Estado.PENDENTE);
    }


    public Medico save(Medico Medico) {
        Optional<Medico> existingMedico = MedicoRepository.findByEmail(Medico.getEmail());
        if (existingMedico.isPresent()) {
            throw new IllegalArgumentException("Este email já está registado, Esqueceu a senha?.");
        }
        Optional<Medico> existingMedicoByContacto = MedicoRepository.findByContacto(Medico.getContacto());
        if (existingMedicoByContacto.isPresent()) {
            throw new IllegalArgumentException("Este contacto já está registado.");
        }
        return MedicoRepository.save(Medico);
    }

    
        public void deleteById(Long id) {
            Optional<Medico> MedicoOpt = MedicoRepository.findById(id);
            if (MedicoOpt.isPresent()) {
                Medico Medico = MedicoOpt.get();
                Medico.setEstado(Estado.ELIMINADO); 
                MedicoRepository.save(Medico);
            } else {
                throw new EntityNotFoundException("Usuário com o ID " + id + " não encontrado.");
            }
        }
       
        public Medico update(Long id, Medico Medico) {
            Optional<Medico> existingMedicoOpt = MedicoRepository.findById(id);
            if (existingMedicoOpt.isPresent()) {
                Medico existingMedico = existingMedicoOpt.get();
                // Atualizar os campos do usuário existente com os novos valores
                existingMedico.setNome(Medico.getNome());
                existingMedico.setEmail(Medico.getEmail());
                existingMedico.setSenha(Medico.getSenha());
                existingMedico.setContacto(Medico.getContacto());
                existingMedico.setData_nasc(Medico.getData_nasc());
                existingMedico.setNum_ordem_medico(Medico.getNum_ordem_medico());
                // Atualize outros campos conforme necessário
    
                return MedicoRepository.save(existingMedico);
            } else {
                throw new EntityNotFoundException("Medico não encontrado " + id);
            }
        }

    public Optional<Medico> findById(Long id) {
        return MedicoRepository.findById(id)
                .filter(Medico -> Medico.getEstado().equals(Estado.ATIVADO));
    }

}
