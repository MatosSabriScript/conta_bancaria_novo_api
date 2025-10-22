package com.senai.conta_bancaria_turma2.application.service;


import com.senai.conta_bancaria_turma2.application.dto.GerenteDTO;
import com.senai.conta_bancaria_turma2.domain.entity.Gerente;
import com.senai.conta_bancaria_turma2.domain.enums.Role;
import com.senai.conta_bancaria_turma2.domain.repository.GerenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GerenteService {

    private final GerenteRepository gerenteRepository;

    private final PasswordEncoder encoder;

    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    public List<GerenteDTO> listarTodosGerentes() {
        return gerenteRepository.findAll().stream()
                .map(GerenteDTO::fromEntity)
                .toList();
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    public GerenteDTO cadastrarGerente(GerenteDTO dto) {
        Gerente entity = dto.toEntity();
        entity.setSenha(encoder.encode(dto.senha()));
        entity.setRole(Role.CLIENTE);
        gerenteRepository.save(entity);
        return GerenteDTO.fromEntity(entity);
    }
}