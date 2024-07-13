package com.example.sausis.model;
import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="usuarios")

public class Usuario {
    @Id
    @Column(name="id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_usuario;

    @Column(name="nome")
    private String nome;

    @Column(name="data_nasc")
    private Date data_nasc;

    @Column(name="contacto")
    private int contacto;

    @Column(name="email" ,nullable=false,unique=true)
    private String email;

    @Column(name="estado")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(name="sexo")
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    
    @Column(name="senha")
    private String senha;

    private LocalDateTime data_registo;

    private LocalDateTime data_atualizacao;

    @PrePersist
protected void onCreate() {
    this.data_registo = LocalDateTime.now();
}

@PreUpdate
protected void onUpdate() {
    this.data_atualizacao = LocalDateTime.now();
    
    
}

    
}
