package com.example.sausis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name="enfermeiros")
@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public final class Enfermeiro extends Funcionario{
    @Column(name="enf_num_ordem")
    private int  enfnNumOrdem;


}
