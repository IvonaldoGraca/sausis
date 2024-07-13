package com.example.sausis.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@DiscriminatorValue("Medico")
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)

public class Medico extends Funcionario{


    @Column(name="num_ordem_medico")
    private int num_ordem_medico;
    
}


