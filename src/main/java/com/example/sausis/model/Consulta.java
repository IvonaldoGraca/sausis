package com.example.sausis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="consultas")

public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private long id_consulta;

  

    @Column(name="estado")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    /*@ManyToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "id_usuario")
    private Medico medico;
*/
    @ManyToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name="id_catgconsulta")
    private CategoriaConsulta categoriaConsula;


}