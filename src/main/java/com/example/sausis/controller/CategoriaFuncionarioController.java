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

import com.example.sausis.model.CategoriaFuncionario;
import com.example.sausis.service.CategoriaFuncionarioService;

import jakarta.persistence.EntityNotFoundException;
import lombok.Data;

@Data
@RestController
@RequestMapping("api/categoriafuncionario")

public class CategoriaFuncionarioController {

    @Autowired
    CategoriaFuncionarioService CategoriaFuncionarioService;
    @PostMapping

    public com.example.sausis.service.CategoriaFuncionarioService getCategoriaFuncionarioService() {
        return CategoriaFuncionarioService;
    }

    public ResponseEntity<?> save(@RequestBody CategoriaFuncionario CategoriaFuncionario) {
        try {
            CategoriaFuncionario CategoriaFuncionarioSalvo = CategoriaFuncionarioService.save(CategoriaFuncionario);
            return new ResponseEntity<>(CategoriaFuncionarioSalvo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

@GetMapping
public List<CategoriaFuncionario> findAll() {
    return CategoriaFuncionarioService.findAll();
    
}

@GetMapping("/active")
public Optional<CategoriaFuncionario> findAllActive() {
    return CategoriaFuncionarioService.findAllActive();
}

@GetMapping("/eliminado")
public Optional<CategoriaFuncionario> findAllDeleted() {
    return CategoriaFuncionarioService.findAllADelteded();
}

@DeleteMapping("/{id}")
public ResponseEntity<String> delete(@PathVariable Long id) {
    try {
        CategoriaFuncionarioService.deleteById(id);
        return ResponseEntity.ok("CategoriaFuncionario eliminado com sucesso.");
    } catch (EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}

@PutMapping("/{id}")
public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody CategoriaFuncionario CategoriaFuncionario) {
    try {
        return ResponseEntity.ok(CategoriaFuncionarioService.update(id, CategoriaFuncionario));
    } catch (EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}

@GetMapping("/{id}")
public ResponseEntity<CategoriaFuncionario> findById(@PathVariable Long id) {
    Optional<CategoriaFuncionario> CategoriaFuncionario = CategoriaFuncionarioService.findById(id);
    return CategoriaFuncionario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}
}
