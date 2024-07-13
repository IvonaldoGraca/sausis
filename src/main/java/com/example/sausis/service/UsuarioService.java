package com.example.sausis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sausis.model.Estado;
import com.example.sausis.model.Usuario;
import com.example.sausis.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
@Service

public class UsuarioService {

    @Autowired UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> findAllActive() {
        return usuarioRepository.findByEstado(Estado.ATIVADO);
    }

    public List<Usuario> findAllADelteded() {
        return usuarioRepository.findByEstado(Estado.ELIMINADO);
    }

    public List<Usuario> findAllAPendente() {
        return usuarioRepository.findByEstado(Estado.PENDENTE);
    }


    public Usuario save(Usuario usuario) {
        Optional<Usuario> existingUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        if (existingUsuario.isPresent()) {
            throw new IllegalArgumentException("Este email já está registado, Esqueceu a senha?.");
        }
        Optional<Usuario> existingUsuarioByContacto = usuarioRepository.findByContacto(usuario.getContacto());
        if (existingUsuarioByContacto.isPresent()) {
            throw new IllegalArgumentException("Este contacto já está registado.");
        }
        return usuarioRepository.save(usuario);
    }

    
        public void deleteById(Long id) {
            Optional<Usuario> UsuarioOpt = usuarioRepository.findById(id);
            if (UsuarioOpt.isPresent()) {
                Usuario usuario = UsuarioOpt.get();
                usuario.setEstado(Estado.ELIMINADO); 
                usuarioRepository.save(usuario);
            } else {
                throw new EntityNotFoundException("Usuário com o ID " + id + " não encontrado.");
            }
        }
    

    

        public Usuario update(Long id, Usuario usuario) {
            Optional<Usuario> existingUsuarioOpt = usuarioRepository.findById(id);
            if (existingUsuarioOpt.isPresent()) {
                Usuario existingUsuario = existingUsuarioOpt.get();
                // Atualizar os campos do usuário existente com os novos valores
                existingUsuario.setNome(usuario.getNome());
                existingUsuario.setEmail(usuario.getEmail());
                existingUsuario.setSenha(usuario.getSenha());
                existingUsuario.setContacto(usuario.getContacto());
                existingUsuario.setData_nasc(usuario.getData_nasc());
                // Atualize outros campos conforme necessário
    
                return usuarioRepository.save(existingUsuario);
            } else {
                throw new EntityNotFoundException("Usuario not found with id " + id);
            }
        }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id)
                .filter(usuario -> usuario.getEstado().equals(Estado.ATIVADO));
    }
}



