package com.example.sausis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name="medicos")
@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class Medico extends Funcionario{


    @Column(name="num_ordem_medico")
    private int num_ordem_medico;
    
}


