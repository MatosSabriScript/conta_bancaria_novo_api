package com.senai.conta_bancaria_turma2.interface_ui;

import com.senai.conta_bancaria_turma2.application.dto.ContaResumoDTO;
import com.senai.conta_bancaria_turma2.application.service.ContService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/conta")
@RequiredArgsConstructor
public class ContaController {
    private final ContService service;

    @GetMapping
    public ResponseEntity<List<ContaResumoDTO>> listarTodasContas() {
        return ResponseEntity.ok(service.listarTodasContas());
    }

    @GetMapping("/{numeroDaConta}")
    public ResponseEntity<ContaResumoDTO> buscarContaPorNumero(@PathVariable String numeroDaConta) {
        return ResponseEntity.ok(service.buscarContaPorNumero(numeroDaConta));
    }

    @PutMapping ("/{numeroDaConta}")
    public ResponseEntity<ContaResumoDTO> atualizarConta(@PathVariable String numeroDaConta) {

        if (conta instanceof ContaCorrenteDTO) {
            conta.setLimite(((ContaCorrenteDTO) conta).limite());

            // lógica específica para ContaCorrente
        } else if (conta instanceof ContaPoupancaDTO) {
            // lógica específica para ContaPoupanca
        }


    }
}
