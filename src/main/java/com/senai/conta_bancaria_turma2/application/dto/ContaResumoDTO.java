package com.senai.conta_bancaria_turma2.application.dto;

import com.senai.conta_bancaria_turma2.domain.entity.Cliente;
import com.senai.conta_bancaria_turma2.domain.entity.Conta;
import com.senai.conta_bancaria_turma2.domain.entity.ContaCorrente;
import com.senai.conta_bancaria_turma2.domain.entity.ContaPoupanca;
import com.senai.conta_bancaria_turma2.domain.exceptions.TipoDeContaInativaException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public record ContaResumoDTO(
        @NotBlank
        String numero,
        @NotNull
        String tipo,
        @NotNull
        BigDecimal saldo
) {
    public Conta toEntity(Cliente cliente){
        if("CORRENTE".equalsIgnoreCase(tipo)){
            return ContaCorrente.builder()
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .ativa(true)
                    .cliente(cliente)
                    .limite(new BigDecimal("500.0"))
                    .taxa(new BigDecimal("0.05"))
                    .build();
        } else if ("POUPANCA".equalsIgnoreCase(tipo)){
            return ContaPoupanca.builder()
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .ativa(true)
                    .rendimento(new BigDecimal("0.01"))
                    .cliente(cliente)
                    .build();
        }
        throw new TipoDeContaInativaException();
    }
    public static ContaResumoDTO fromEntity(Conta c) {
        return new ContaResumoDTO(
                c.getNumero(),
                c.getTipo(),
                c.getSaldo()
        );
    }
}

