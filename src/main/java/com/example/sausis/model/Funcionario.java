package com.example.sausis.model;

import java.sql.Date;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name="funcionarios")
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
@Data


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
