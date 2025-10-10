package com.senai.conta_bancaria_turma2.application.dto;

import com.senai.conta_bancaria_turma2.domain.entity.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record ClienteResponseDTO(
        String id,
        @NotBlank
        String nome,
        @NotNull
        @Pattern(regexp="\\d{11}", message="CPF deve conter exatamente 11 dígitos numéricos")
        String cpf,
        List<ContaResumoDTO> contas
) {
    public static ClienteResponseDTO fromEntity(Cliente cliente) {
        List<ContaResumoDTO> contas = cliente.getContas().stream()
                .map(ContaResumoDTO::fromEntity)
                .toList();
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                contas
        );
    }
}
