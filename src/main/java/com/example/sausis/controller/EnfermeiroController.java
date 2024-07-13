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

import com.example.sausis.model.Enfermeiro;
import com.example.sausis.service.EnfermeiroService;

import jakarta.persistence.EntityNotFoundException;
import lombok.Data;

@Data
@RestController
@RequestMapping("api/enfermeiro")

public class EnfermeiroController {

    @Autowired
    EnfermeiroService enfermeiroService;
    @PostMapping
public ResponseEntity<?> create(@RequestBody Enfermeiro enfermeiro) {
        try {
            Enfermeiro enfermeiroSalvo = enfermeiroService.save(enfermeiro);
            return new ResponseEntity<>(enfermeiroSalvo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

@GetMapping
public List<Enfermeiro> findAll() {
    return enfermeiroService.findAll();
    
}

@GetMapping("/active")
public List<Enfermeiro> findAllActive() {
    return enfermeiroService.findAllActive();
}

@GetMapping("/eliminado")
public List<Enfermeiro> findAllDeleted() {
    return enfermeiroService.findAllADelteded();
}

@DeleteMapping("/{id}")
public ResponseEntity<String> delete(@PathVariable Long id) {
    try {
        enfermeiroService.deleteById(id);
        return ResponseEntity.ok("enfermeiro eliminado com sucesso.");
    } catch (EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}

@PutMapping("/{id}")
public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Enfermeiro enfermeiro) {
    try {
        return ResponseEntity.ok(enfermeiroService.update(id, enfermeiro));
    } catch (EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}

@GetMapping("/{id}")
public ResponseEntity<Enfermeiro> findById(@PathVariable Long id) {
    Optional<Enfermeiro> enfermeiro = enfermeiroService.findById(id);
    return enfermeiro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}
}
