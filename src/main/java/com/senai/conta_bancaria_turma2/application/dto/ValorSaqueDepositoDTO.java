package com.senai.conta_bancaria_turma2.application.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public record ValorSaqueDepositoDTO(
        @NotNull
        BigDecimal valor) {
    }

