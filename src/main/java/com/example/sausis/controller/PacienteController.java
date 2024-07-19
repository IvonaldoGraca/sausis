package com.example.sausis.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.sausis.dto.LoginRequestDTO;
import com.example.sausis.dto.ResponseDTO;
import com.example.sausis.model.Paciente;
import com.example.sausis.repository.PacienteRepository;
import com.example.sausis.security.TokenService;
import com.example.sausis.service.PacienteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;

@RestController
@RequestMapping("api/paciente")
@Data
public class PacienteController {

    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Autowired
    private final PacienteService pacienteService;
    @Autowired
    private final PacienteRepository pacienteRepository;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Paciente paciente) {
        Optional<Paciente> pacienteExistente = pacienteService.findByEmail(paciente.getEmail());
        
        if (pacienteExistente.isEmpty()) {
            Paciente novoPaciente = new Paciente();
            novoPaciente.setSenha(passwordEncoder.encode(paciente.getSenha()));
            novoPaciente.setEmail(paciente.getEmail());
            novoPaciente.setNome(paciente.getNome());
            novoPaciente.setData_nasc(paciente.getData_nasc());
            novoPaciente.setContacto(paciente.getContacto());
            novoPaciente.setEstado(paciente.getEstado());
            novoPaciente.setSexo(paciente.getSexo());

            Paciente pacienteSalvo = pacienteService.save(novoPaciente);

            String token = tokenService.generateToken(pacienteSalvo);
            return ResponseEntity.ok(new ResponseDTO(pacienteSalvo.getEmail(), token));
        }
        
        return ResponseEntity.badRequest().body("Paciente já existe com este e-mail.");
    }


    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
        Paciente paciente = this.pacienteRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(body.senha(), paciente.getSenha())) {
            String token = this.tokenService.generateToken(paciente);
            return ResponseEntity.ok(new ResponseDTO(paciente.getEmail(), token));
        }
        return ResponseEntity.badRequest().build();
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
    public ResponseEntity<Paciente> update(@PathVariable Long id, @RequestBody Paciente paciente) {
        try {
            return ResponseEntity.ok(pacienteService.update(id, paciente));
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