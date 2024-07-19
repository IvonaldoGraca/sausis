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
        Optional<Paciente> pacienteExistentePorEmail = pacienteService.findByEmail(paciente.getEmail());
        Optional<Paciente> pacienteExistentePorContacto = pacienteService.findByContacto(paciente.getContacto());
        
        if (pacienteExistentePorEmail.isPresent()) {
            return ResponseEntity.badRequest().body("já existe um paciente registado com este e-mail.");
        }
    
        if (pacienteExistentePorContacto.isPresent()) {
            return ResponseEntity.badRequest().body("já existe um paciente registado com este contacto.");
        }
    
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


    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequestDTO body) {
    // Verifica se o e-mail está vazio ou nulo
    if (body.email() == null || body.email().isEmpty()) {
        return ResponseEntity.badRequest().body("O e-mail não pode estar vazio, Digite o seu email.");
    }

    // Verifica se a senha está vazia ou nula
    if (body.senha() == null || body.senha().isEmpty()) {
        return ResponseEntity.badRequest().body("A senha não pode estar vazia.");
    }

    // Procura o paciente pelo e-mail
    Paciente paciente = pacienteRepository.findByEmail(body.email())
        .orElse(null);

    // Verifica se o paciente foi encontrado
    if (paciente == null) {
        return ResponseEntity.badRequest().body("Usuário não encontrado com o e-mail fornecido.");
    }

    // Verifica se a senha está correta
    if (!passwordEncoder.matches(body.senha(), paciente.getSenha())) {
        return ResponseEntity.badRequest().body("Senha incorreta.");
    }

    String token = tokenService.generateToken(paciente);
    return ResponseEntity.ok(new ResponseDTO(paciente.getEmail(), token));
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