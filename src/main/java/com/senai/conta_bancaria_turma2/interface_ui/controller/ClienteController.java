package com.senai.conta_bancaria_turma2.interface_ui.controller;

import com.senai.conta_bancaria_turma2.application.dto.ClienteRegistroDTO;
import com.senai.conta_bancaria_turma2.application.dto.ClienteResponseDTO;
import com.senai.conta_bancaria_turma2.application.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;


    @PostMapping
    public ResponseEntity<ClienteResponseDTO> registrarCliente(@RequestBody @Valid ClienteRegistroDTO dto) {
       ClienteResponseDTO novoCliente = service.registarClienteOuAnexarConta(dto);
        return ResponseEntity.created(
                URI.create("/api/cliente/cpf/"+novoCliente.cpf())
        ).body(novoCliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarClientesAtivos() {
        return ResponseEntity.ok(service.listarClientesAtivos());
    }
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteResponseDTO> buscarClientePorCpf(@PathVariable @Valid String cpf) {
        return ResponseEntity.ok(service.buscarClientePorCpf(cpf));
    }

    @PutMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable String cpf, @RequestBody @Valid  ClienteRegistroDTO dto) {

        return ResponseEntity.ok(service.atualizarCliente(dto, cpf));
    }

    @DeleteMapping("/cpf/{cpf}")
    public ResponseEntity<Void> deletarCliente(@PathVariable String cpf) {
        service.deletarCliente(cpf);
        return ResponseEntity.noContent().build();
    }

}
