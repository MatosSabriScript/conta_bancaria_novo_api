package com.senai.conta_bancaria_turma2.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(
        name = "gerente",
        uniqueConstraints = @UniqueConstraint(name = "uk_gerente_cpf", columnNames = "cpf")
)
public class Gerente extends Usuario {


}