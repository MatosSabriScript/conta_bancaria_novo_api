package com.senai.conta_bancaria_turma2.domain.repository;

import com.senai.conta_bancaria_turma2.domain.entity.Taxas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaxasRepository extends JpaRepository<Taxas, Long> {
    Optional <Taxas> findByDescricao(String descricao);
}
