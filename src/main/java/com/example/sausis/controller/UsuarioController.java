package com.example.sausis.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sausis.model.Usuario;
import com.example.sausis.service.UsuarioService;

import jakarta.persistence.EntityNotFoundException;
import lombok.Data;

@RestController
@RequestMapping("api/usuario")
@Data
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;
    @PostMapping
public ResponseEntity<?> create(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioSalvo = usuarioService.save(usuario);
            return new ResponseEntity<>(usuarioSalvo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

@GetMapping
public List<Usuario> findAll() {
    return usuarioService.findAll();
    
}

@GetMapping("/active")
public List<Usuario> findAllActive() {
    return usuarioService.findAllActive();
}

@GetMapping("/eliminado")
public List<Usuario> findAllDeleted() {
    return usuarioService.findAllADelteded();
}

@DeleteMapping("/{id}")
public ResponseEntity<String> delete(@PathVariable Long id) {
    try {
        usuarioService.deleteById(id);
        return ResponseEntity.ok("Usuario eliminado com sucesso.");
    } catch (EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}

@PutMapping("/{id}")
public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
    try {
        return ResponseEntity.ok(usuarioService.update(id, usuario));
    } catch (EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}

@GetMapping("/{id}")
public ResponseEntity<Usuario> findById(@PathVariable Long id) {
    Optional<Usuario> complicacoesGravida = usuarioService.findById(id);
    return complicacoesGravida.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}
}

