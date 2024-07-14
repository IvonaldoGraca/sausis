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

import com.example.sausis.model.CategoriaConsulta;
import com.example.sausis.service.CategoriaConsultaService;

import jakarta.persistence.EntityNotFoundException;
import lombok.Data;

@Data
@RestController
@RequestMapping("api/categoriaconsulta")

public class CategoriaConsultaController {

    @Autowired
    CategoriaConsultaService categoriaConsultaService;
    @PostMapping
public ResponseEntity<?> create(@RequestBody CategoriaConsulta categoriaConsulta) {
        try {
            CategoriaConsulta CategoriaConsultaSalvo = categoriaConsultaService.save(categoriaConsulta);
            return new ResponseEntity<>(CategoriaConsultaSalvo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

@GetMapping
public List<CategoriaConsulta> findAll() {
    return categoriaConsultaService.findAll();
    
}

@GetMapping("/active")
public Optional<CategoriaConsulta> findAllActive() {
    return categoriaConsultaService.findAllActive();
}

@GetMapping("/eliminado")
public Optional<CategoriaConsulta> findAllDeleted() {
    return categoriaConsultaService.findAllADelteded();
}

@DeleteMapping("/{id}")
public ResponseEntity<String> delete(@PathVariable Long id) {
    try {
        categoriaConsultaService.deleteById(id);
        return ResponseEntity.ok("Categoria de Consulta eliminada com sucesso.");
    } catch (EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}

@PutMapping("/{id}")
public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody CategoriaConsulta categoriaConsulta) {
    try {
        return ResponseEntity.ok(categoriaConsultaService.update(id, categoriaConsulta));
    } catch (EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}

@GetMapping("/{id}")
public ResponseEntity<CategoriaConsulta> findById(@PathVariable Long id) {
    Optional<CategoriaConsulta> CategoriaConsulta = categoriaConsultaService.findById(id);
    return CategoriaConsulta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}
}
