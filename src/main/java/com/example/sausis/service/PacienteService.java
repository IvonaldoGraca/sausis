package com.example.sausis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sausis.model.Estado;
import com.example.sausis.model.Paciente;
import com.example.sausis.repository.PacienteRepository;

import jakarta.persistence.EntityNotFoundException;
@Service

public class PacienteService {

    @Autowired PacienteRepository PacienteRepository;

    public List<Paciente> findAll() {
        return PacienteRepository.findAll();
    }

    public List<Paciente> findAllActive() {
        return PacienteRepository.findByEstado(Estado.ATIVADO);
    }

    public List<Paciente> findAllADelteded() {
        return PacienteRepository.findByEstado(Estado.ELIMINADO);
    }

    public List<Paciente> findAllAPendente() {
        return PacienteRepository.findByEstado(Estado.PENDENTE);
    }


    public Paciente save(Paciente Paciente) {
        Optional<Paciente> existingPaciente = PacienteRepository.findByEmail(Paciente.getEmail());
        if (existingPaciente.isPresent()) {
            throw new IllegalArgumentException("Este email já está registado, Esqueceu a senha?.");
        }
        Optional<Paciente> existingPacienteByContacto = PacienteRepository.findByContacto(Paciente.getContacto());
        if (existingPacienteByContacto.isPresent()) {
            throw new IllegalArgumentException("Este contacto já está registado.");
        }
        return PacienteRepository.save(Paciente);
    }

    
        public void deleteById(Long id) {
            Optional<Paciente> PacienteOpt = PacienteRepository.findById(id);
            if (PacienteOpt.isPresent()) {
                Paciente Paciente = PacienteOpt.get();
                Paciente.setEstado(Estado.ELIMINADO); 
                PacienteRepository.save(Paciente);
            } else {
                throw new EntityNotFoundException("Usuário com o ID " + id + " não encontrado.");
            }
        }
    

    

        public Paciente update(Long id, Paciente Paciente) {
            Optional<Paciente> existingPacienteOpt = PacienteRepository.findById(id);
            if (existingPacienteOpt.isPresent()) {
                Paciente existingPaciente = existingPacienteOpt.get();
                // Atualizar os campos do usuário existente com os novos valores
                existingPaciente.setNome(Paciente.getNome());
                existingPaciente.setEmail(Paciente.getEmail());
                existingPaciente.setSenha(Paciente.getSenha());
                existingPaciente.setContacto(Paciente.getContacto());
                existingPaciente.setData_nasc(Paciente.getData_nasc());
                // Atualize outros campos conforme necessário
    
                return PacienteRepository.save(existingPaciente);
            } else {
                throw new EntityNotFoundException("Paciente not found with id " + id);
            }
        }

    public Optional<Paciente> findById(Long id) {
        return PacienteRepository.findById(id)
                .filter(Paciente -> Paciente.getEstado().equals(Estado.ATIVADO));
    }
}



