package com.example.sausis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Table(name="pacientes")
@Entity
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

public class Paciente extends Usuario{


    
}
