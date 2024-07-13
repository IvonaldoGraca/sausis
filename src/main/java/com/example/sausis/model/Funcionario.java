package com.example.sausis.model;

import java.sql.Date;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="funcionarios")
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)




public class Funcionario {

    @Id
    @Column(name="id_funcionario")
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

    @Column(name="num_funcionario")
    private int num_funcionario;

    @Column(name="data_admissao")
    private Date data_admissao;

    @Column(name="data_demissao")
    private Date data_demissao;

    @Column(name="entrada")
    private LocalTime entrada;

    @Column(name="saida")
    private LocalTime saida;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;
    
    @ManyToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name="id_catgfuncionario")
    private CategoriaFuncionario categoriaFuncionario;
}
