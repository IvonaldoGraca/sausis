package com.example.sausis.model;

import java.sql.Date;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="funcionarios")


public class Funcionario extends Usuario {

    @Column(name="num_funcionario")
    private int num_funcionario;

    @Column(name="data_admissao")
    private Date data_admissao;

    @Column(name="data_demissao")
    private Date data_demissao;

    @Column(name="horario")
    private LocalTime horario;
    
}
