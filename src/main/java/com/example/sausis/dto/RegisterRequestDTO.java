package com.example.sausis.dto;

import java.sql.Date;

import com.example.sausis.model.Estado;
import com.example.sausis.model.Sexo;

import lombok.Data;

@Data

public class RegisterRequestDTO {

    private String nome;
    private Date data_nasc;
    private int contacto;
    private String email;
    private Estado estado;
    private Sexo sexo;
    private String senha;

    public RegisterRequestDTO(String nome, Date data_nasc, int contacto, String email, Estado estado, Sexo sexo, String senha) {

    }


}