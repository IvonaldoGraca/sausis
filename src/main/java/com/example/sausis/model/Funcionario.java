package com.example.sausis.model;

import java.sql.Date;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@DiscriminatorValue("Funcionario")
@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)


public class Funcionario extends Usuario {

    @Column(name="num_funcionario")
    private int num_funcionario;

    @Column(name="data_admissao")
    private Date data_admissao;

    @Column(name="data_demissao")
    private Date data_demissao;

    @Column(name="horario")
    private LocalTime horario;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Departamento departamento;
    
    @ManyToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name="id_catgfuncionario")
    private CategoriaFuncionario categoriaFuncionario;
}
