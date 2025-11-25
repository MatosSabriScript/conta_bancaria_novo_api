package com.senai.conta_bancaria_turma2.interface_ui.controller;

import com.senai.conta_bancaria_turma2.domain.entity.Taxas;
import com.senai.conta_bancaria_turma2.domain.repository.TaxasRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/taxas")
@SecurityRequirement(name = "bearerAuth")
public class TaxaController {

    private final TaxasRepository repo;

    public TaxaController(TaxasRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('GERENTE','ADMIN')")
    public List<Taxas> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('GERENTE','ADMIN')")
    public ResponseEntity<Taxas> buscar(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('GERENTE','ADMIN')")
    public ResponseEntity<Taxas> criar(@RequestBody Taxas dto) {
        Taxas salvo = repo.save(dto);
        return ResponseEntity.created(URI.create("/taxas/" + salvo.getId())).body(salvo);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('GERENTE','ADMIN')")
    public ResponseEntity<Taxas> atualizar(@PathVariable Long id, @RequestBody Taxas dto) {
        return repo.findById(id)
                .map(existente -> {
                    existente.setDescricao(dto.getDescricao());
                    existente.setPercentual(dto.getPercentual());
                    existente.setValorFixo(dto.getValorFixo());
                    return ResponseEntity.ok(repo.save(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('GERENTE','ADMIN')")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
