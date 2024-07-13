package com.example.sausis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name="dep_enfermagem")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DepartamentoEnfermagem extends Departamento {
    

}
