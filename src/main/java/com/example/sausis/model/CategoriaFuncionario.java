package com.example.sausis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data

public class CategoriaFuncionario {

    @Id
    @Column(name="id_catgfuncionario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_catgfuncionario;
    
    @Column(name="categoria_funcionario")
    private String nome;
}
