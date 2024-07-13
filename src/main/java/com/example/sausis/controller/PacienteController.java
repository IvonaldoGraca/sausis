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

import com.example.sausis.model.Paciente;
import com.example.sausis.service.PacienteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;

@RestController
@RequestMapping("api/paciente")
@Data
public class PacienteController {

    @Autowired
    PacienteService pacienteService;
    @PostMapping
public ResponseEntity<?> create(@RequestBody Paciente paciente) {
        try {
            Paciente pacienteSalvo = pacienteService.save(paciente);
            return new ResponseEntity<>(pacienteSalvo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

@GetMapping
public List<Paciente> findAll() {
    return pacienteService.findAll();
    
}

@GetMapping("/active")
public List<Paciente> findAllActive() {
    return pacienteService.findAllActive();
}

@GetMapping("/eliminado")
public List<Paciente> findAllDeleted() {
    return pacienteService.findAllADelteded();
}

@DeleteMapping("/{id}")
public ResponseEntity<String> delete(@PathVariable Long id) {
    try {
        pacienteService.deleteById(id);
        return ResponseEntity.ok("paciente eliminado com sucesso.");
    } catch (EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}

@PutMapping("/{id}")
public ResponseEntity<Paciente> update(@PathVariable Long id, @RequestBody Paciente Paciente) {
    try {
        return ResponseEntity.ok(pacienteService.update(id, Paciente));
    } catch (EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}

@GetMapping("/{id}")
public ResponseEntity<Paciente> findById(@PathVariable Long id) {
    Optional<Paciente> paciente = pacienteService.findById(id);
    return paciente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}
}
