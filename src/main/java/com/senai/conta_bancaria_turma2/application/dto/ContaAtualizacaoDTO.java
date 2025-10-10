package com.senai.conta_bancaria_turma2.application.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ContaAtualizacaoDTO (
        @NotNull
        BigDecimal saldo,
        @NotNull
        BigDecimal limite,
        @NotNull
        BigDecimal rendimento,
        @NotNull
        BigDecimal taxa){


}
