package com.senai.conta_bancaria_turma2.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@Table(name = "taxa")



public class Taxas {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private String id;

    @Column (nullable = false, unique = true)
    @Enumerated (EnumType.STRING)
    private TaxasDescriçao descricao;

    @Column(nullable = false)
    private Double percentual;

    @Column(nullable = false)
    private Double valorFixo;


}
