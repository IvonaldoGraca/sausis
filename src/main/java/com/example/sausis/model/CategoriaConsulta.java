package com.example.sausis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="categoria_consulta")
public class CategoriaConsulta {

    @Id
    @Column(name="id_catgconsulta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_catgconsulta;

    @Column(name="categoria")
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name="estado")
    private Estado estado;
    
}
