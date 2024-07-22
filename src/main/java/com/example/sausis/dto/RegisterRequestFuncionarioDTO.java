package com.example.sausis.dto;

import java.sql.Date;
import java.time.LocalTime;

import com.example.sausis.model.Estado;
import com.example.sausis.model.Sexo;

import lombok.Data;

@Data
public class RegisterRequestFuncionarioDTO {


    private String nome;
    private Date data_nasc;
    private int contacto;
    private String email;
    private Estado estado;
    private Sexo sexo;
    private String senha;
    private int num_funcionario;
    private Date data_admissao;
    private Date data_demissao;
    private LocalTime entrada;
    private LocalTime saida;


    public RegisterRequestFuncionarioDTO(String nome, Date data_nasc, int contacto, String email, Estado estado, Sexo sexo, String senha,int num_funcionario,Date data_admissao,Date data_demissao,LocalTime entrada, LocalTime saida) {

    }
}
