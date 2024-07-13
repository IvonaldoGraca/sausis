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

import com.example.sausis.model.Medico;
import com.example.sausis.service.MedicoService;

import jakarta.persistence.EntityNotFoundException;
import lombok.Data;

@Data
@RestController
@RequestMapping("api/medico")

public class MedicoController {

    @Autowired
    MedicoService medicoService;
    @PostMapping
public ResponseEntity<?> create(@RequestBody Medico medico) {
        try {
            Medico medicoSalvo = medicoService.save(medico);
            return new ResponseEntity<>(medicoSalvo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

@GetMapping
public List<Medico> findAll() {
    return medicoService.findAll();
    
}

@GetMapping("/active")
public List<Medico> findAllActive() {
    return medicoService.findAllActive();
}

@GetMapping("/eliminado")
public List<Medico> findAllDeleted() {
    return medicoService.findAllADelteded();
}

@DeleteMapping("/{id}")
public ResponseEntity<String> delete(@PathVariable Long id) {
    try {
        medicoService.deleteById(id);
        return ResponseEntity.ok("medico eliminado com sucesso.");
    } catch (EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}

@PutMapping("/{id}")
public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Medico medico) {
    try {
        return ResponseEntity.ok(medicoService.update(id, medico));
    } catch (EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}

@GetMapping("/{id}")
public ResponseEntity<Medico> findById(@PathVariable Long id) {
    Optional<Medico> medico = medicoService.findById(id);
    return medico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}
}
