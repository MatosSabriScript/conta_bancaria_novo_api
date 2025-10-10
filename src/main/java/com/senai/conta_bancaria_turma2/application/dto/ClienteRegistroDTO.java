package com.senai.conta_bancaria_turma2.application.dto;

import com.senai.conta_bancaria_turma2.domain.entity.Cliente;
import com.senai.conta_bancaria_turma2.domain.entity.Conta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClienteRegistroDTO(
        @NotBlank
        String nome,
        @Pattern(regexp="\\d{11}", message="CPF deve conter exatamente 11 dígitos numéricos")
        String cpf,
        @NotBlank
        ContaResumoDTO contas
) {
    public Cliente toEntity() {
        return Cliente.builder()
                .ativo(true)
                .nome(this.nome)
                .cpf(this.cpf)
                .contas(new ArrayList<Conta>())
                .build();
    }
}