package com.example.sausis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="departamentos")
@Data
@Inheritance(strategy=InheritanceType.JOINED)
public class Departamento {

    @Id
    @Column(name="id_departamento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_departamento;

    @Column(name="departamento",nullable = false,unique = true)
    private String nome;

     @Column(name="estado")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    
}
