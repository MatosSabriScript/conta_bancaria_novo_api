package com.senai.conta_bancaria_turma2.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.zip.DataFormatException;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@Table(name = "pagamento",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_pagamento_numero", columnNames = "numeroPagamento")
        }
)
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String boleto;
    private LocalDateTime dataPagamento;
    private Enum status;
    private Double valorPago;

    // Associação com a Conta
    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

    private String descricao;

    private String status;

    // Métodos de lógica de pagamento podem ser adicionados aqui
}
