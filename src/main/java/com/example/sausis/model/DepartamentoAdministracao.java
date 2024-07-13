package com.example.sausis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name="dep_administracao")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DepartamentoAdministracao extends Departamento{

    
}
