package com.example.sausis.security;

import com.example.sausis.repository.FuncionarioRepository;
import com.example.sausis.model.Funcionario;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilterFuncionario extends OncePerRequestFilter {

    @Autowired
    private TokenServiceFuncionario tokenServiceFuncionario;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request);

        if (token != null) {
            String login = tokenServiceFuncionario.validateToken(token);
            if (login != null) {
                Funcionario funcionario = funcionarioRepository.findByEmail(login)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

                var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
                var authentication = new UsernamePasswordAuthenticationToken(funcionario, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}
