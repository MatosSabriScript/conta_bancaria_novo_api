package com.senai.conta_bancaria_turma2.interface_ui.controller;


import com.senai.conta_bancaria_turma2.application.dto.GerenteDTO;
import com.senai.conta_bancaria_turma2.application.service.GerenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gerente")
@RequiredArgsConstructor
public class GerenteController {

    private final GerenteService service;

    @GetMapping
    public ResponseEntity<List<GerenteDTO>> listarTodosGerentes() {
        List<GerenteDTO> gerentes = service.listarTodosGerentes();
        return ResponseEntity.ok(gerentes);
    }

    @PostMapping
    public ResponseEntity<GerenteDTO> cadastrarGerente(@RequestBody GerenteDTO dto) {
        GerenteDTO professorCriado = service.cadastrarGerente(dto);
        return ResponseEntity.ok(professorCriado);
    }
}