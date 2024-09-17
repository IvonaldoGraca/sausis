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

import com.example.sausis.model.Departamento;
import com.example.sausis.service.DepartamentoService;

import jakarta.persistence.EntityNotFoundException;
import lombok.Data;

@Data
@RestController
@RequestMapping("api/departamento")

public class DepartamentoController {

    @Autowired
    DepartamentoService departamentoService;
    @PostMapping("/create")
public ResponseEntity<?> create(@RequestBody Departamento departamento) {
        try {
            Departamento departamentoSalvo = departamentoService.save(departamento);
            return new ResponseEntity<>(departamentoSalvo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

@GetMapping
public List<Departamento> findAll() {
    return departamentoService.findAll();
    
}

@GetMapping("/active")
public Optional<Departamento> findAllActive() {
    return departamentoService.findAllActive();
}

@GetMapping("/eliminado")
public Optional<Departamento> findAllDeleted() {
    return departamentoService.findAllADelteded();
}

@DeleteMapping("/{id}")
public ResponseEntity<String> delete(@PathVariable Long id) {
    try {
        departamentoService.deleteById(id);
        return ResponseEntity.ok("departamento eliminado com sucesso.");
    } catch (EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}

@PutMapping("/{id}")
public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Departamento departamento) {
    try {
        return ResponseEntity.ok(departamentoService.update(id, departamento));
    } catch (EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}

@GetMapping("/{id}")
public ResponseEntity<Departamento> findById(@PathVariable Long id) {
    Optional<Departamento> departamento = departamentoService.findById(id);
    return departamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}
}
