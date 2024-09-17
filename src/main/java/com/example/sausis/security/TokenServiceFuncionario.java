package com.example.sausis.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.sausis.model.Funcionario;

import lombok.Data;

@Service
@Data
public class TokenServiceFuncionario {

    @Value("${api.security.toke.secret}")
    private String secret;

    public String generateToken(Funcionario funcionario){
        try {
            Algorithm algorithm=Algorithm.HMAC256(secret);
            String token=JWT.create()
            .withIssuer("sausis")
            .withSubject(funcionario.getEmail())
            .withExpiresAt(this.generateExpirationDate())
            .sign(algorithm);
            return token;

            
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao efetuar autenticação");
        }
        
    }

    public String validateToken(String token){

        try {
            Algorithm algorithm=Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
            .withIssuer("sausis")
            .build()
            .verify(token)
            .getSubject();
            
        } catch (JWTVerificationException exception) {
            return null;
        }

    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("+01:00"));
    }
    
}
