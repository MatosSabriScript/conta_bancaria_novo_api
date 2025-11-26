package com.senai.conta_bancaria_turma2.domain.repository;

import com.senai.conta_bancaria_turma2.domain.entity.Cliente;
import com.senai.conta_bancaria_turma2.domain.entity.DispositivoIoT;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DispositivoIoTRepository extends JpaRepository<DispositivoIoT, Long> {
    Optional<DispositivoIoT> findByClienteAndAtivoTrue(Cliente cliente);
}