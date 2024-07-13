package com.example.sausis.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)
@DiscriminatorValue("Enfermeiro")

public final class Enfermeiro extends Funcionario{
    @Column(name="enf_num_ordem")
    private int  enfnNumOrdem;


}
