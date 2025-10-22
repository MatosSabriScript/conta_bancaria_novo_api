package com.senai.conta_bancaria_turma2.application.dto;

import com.senai.conta_bancaria_turma2.domain.entity.Cliente;
import com.senai.conta_bancaria_turma2.domain.entity.Conta;
import com.senai.conta_bancaria_turma2.domain.enums.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClienteRegistroDTO(
        @NotBlank(message = "Nome é obrigatório.")
        String nome,

        @NotBlank
        String cpf,

        @NotBlank
        String email,

        @NotBlank
        String senha,

        @Valid
        ContaResumoDTO contaDTO
) {
    public Cliente toEntity() {
        return Cliente.builder()
                .ativo(true)
                .nome(this.nome)
                .cpf(this.cpf)
                .email(this.email)
                .senha(this.senha)
                .role(Role.CLIENTE)
                .contas(new ArrayList<>())
                .build();
    };
    }
