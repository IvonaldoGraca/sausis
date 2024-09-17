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

import com.example.sausis.model.Funcionario;
import com.example.sausis.service.FuncionarioService;

import jakarta.persistence.EntityNotFoundException;
import lombok.Data;

@Data
@RestController
@RequestMapping("api/funcionario")

public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;
    @PostMapping
public ResponseEntity<?> create(@RequestBody Funcionario funcionario) {
        try {
            Funcionario funcionarioSalvo = funcionarioService.save(funcionario);
            return new ResponseEntity<>(funcionarioSalvo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

@GetMapping
public List<Funcionario> findAll() {
    return funcionarioService.findAll();
    
}

@GetMapping("/active")
public List<Funcionario> findAllActive() {
    return funcionarioService.findAllActive();
}

    @DeleteMapping("/{id}")
public ResponseEntity<String> delete(@PathVariable Long id) {
    try {
        funcionarioService.deleteById(id);
        return ResponseEntity.ok("Funcionario eliminado com sucesso.");
    } catch (EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}

@PutMapping("/{id}")
public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Funcionario Funcionario) {
    try {
        return ResponseEntity.ok(funcionarioService.update(id, Funcionario));
    } catch (EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}

@GetMapping("/{id}")
public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
    Optional<Funcionario> funcionario = funcionarioService.findById(id);
    return funcionario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}
}
