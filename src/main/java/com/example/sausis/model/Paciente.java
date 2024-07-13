package com.example.sausis.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;



@Data
@Entity
@DiscriminatorValue("Paciente")

public class Paciente extends Usuario{


    
}
