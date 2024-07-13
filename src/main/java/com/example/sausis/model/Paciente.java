package com.example.sausis.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;



@Data
@Entity
@DiscriminatorValue("Paciente")
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)

public class Paciente extends Usuario{


    
}
