package com.example.sausis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sausis.model.Estado;
import com.example.sausis.model.Funcionario;
import com.example.sausis.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
@Service

public class FuncionarioService {

    @Autowired FuncionarioRepository FuncionarioRepository;

    public List<Funcionario> findAll() {
        return FuncionarioRepository.findAll();
    }

    public List<Funcionario> findAllActive() {
        return FuncionarioRepository.findByEstado(Estado.ATIVADO);
    }

    public List<Funcionario> findAllADelteded() {
        return FuncionarioRepository.findByEstado(Estado.ELIMINADO);
    }

    public List<Funcionario> findAllPendente() {
        return FuncionarioRepository.findByEstado(Estado.PENDENTE);
    }


    public Funcionario save(Funcionario Funcionario) {
        Optional<Funcionario> existingFuncionario = FuncionarioRepository.findByEmail(Funcionario.getEmail());
        if (existingFuncionario.isPresent()) {
            throw new IllegalArgumentException("Este email já está registado, Esqueceu a senha?.");
        }
        Optional<Funcionario> existingFuncionarioByContacto = FuncionarioRepository.findByContacto(Funcionario.getContacto());
        if (existingFuncionarioByContacto.isPresent()) {
            throw new IllegalArgumentException("Este contacto já está registado.");
        }
        return FuncionarioRepository.save(Funcionario);
    }

    
        public void deleteById(Long id) {
            Optional<Funcionario> FuncionarioOpt = FuncionarioRepository.findById(id);
            if (FuncionarioOpt.isPresent()) {
                Funcionario Funcionario = FuncionarioOpt.get();
                Funcionario.setEstado(Estado.ELIMINADO); 
                FuncionarioRepository.save(Funcionario);
            } else {
                throw new EntityNotFoundException("Usuário com o ID " + id + " não encontrado.");
            }
        }
       
        public Funcionario update(Long id, Funcionario Funcionario) {
            Optional<Funcionario> existingFuncionarioOpt = FuncionarioRepository.findById(id);
            if (existingFuncionarioOpt.isPresent()) {
                Funcionario existingFuncionario = existingFuncionarioOpt.get();
                // Atualizar os campos do usuário existente com os novos valores
                existingFuncionario.setNome(Funcionario.getNome());
                existingFuncionario.setEmail(Funcionario.getEmail());
                existingFuncionario.setSenha(Funcionario.getSenha());
                existingFuncionario.setContacto(Funcionario.getContacto());
                existingFuncionario.setData_nasc(Funcionario.getData_nasc());
                // Atualize outros campos conforme necessário
    
                return FuncionarioRepository.save(existingFuncionario);
            } else {
                throw new EntityNotFoundException("Funcionario não encontrado " + id);
            }
        }

    public Optional<Funcionario> findById(Long id) {
        return FuncionarioRepository.findById(id)
                .filter(Funcionario -> Funcionario.getEstado().equals(Estado.ATIVADO));
    }

}
