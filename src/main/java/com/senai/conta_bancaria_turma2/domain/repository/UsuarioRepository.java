package com.senai.conta_bancaria_turma2.domain.repository;

import com.senai.conta_bancaria_turma2.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);
}
