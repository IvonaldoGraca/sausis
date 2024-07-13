package com.example.sausis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name="depart_medico")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)


public class DepartamentoMedico extends Departamento {


    
}
