package com.senai.conta_bancaria_turma2.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "gerente",
        uniqueConstraints = @UniqueConstraint(name = "uk_gerente_cpf", columnNames = "cpf")
)
public class Gerente extends Usuario {

    @OneToMany(mappedBy = "gerente", cascade = CascadeType.ALL)
    private List<Conta> contas;


}