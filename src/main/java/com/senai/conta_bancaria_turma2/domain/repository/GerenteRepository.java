package com.senai.conta_bancaria_turma2.domain.repository;

import com.senai.conta_bancaria_turma2.domain.entity.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GerenteRepository extends JpaRepository<Gerente,String> {
    Optional <Gerente> findByCpfAndAtivoTrue(String cpf);

    List<Gerente>findAllByAtivoTrue();
}
