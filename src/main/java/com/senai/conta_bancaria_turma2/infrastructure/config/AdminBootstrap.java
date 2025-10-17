// java
package com.senai.conta_bancaria_turma2.infrastructure.config;

import com.senai.conta_bancaria_turma2.domain.entity.Gerente;
import com.senai.conta_bancaria_turma2.domain.repository.GerenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminBootstrap implements CommandLineRunner {

    private final GerenteRepository gerenteRepository;

    @Override
    public void run(String... args) {
        // se não existir nenhum gerente, cria um provisório; caso contrário garante que o primeiro esteja ativo
        if (gerenteRepository.count() == 0) {
            Gerente admin = new Gerente();
            admin.setNome("Administrador Provisório");
            admin.setCpf("000.000.000-00");
            admin.setAtivo(Boolean.TRUE);
            gerenteRepository.save(admin);
            System.out.println("⚡ Usuário admin provisório criado");
            return;
        }

        gerenteRepository.findAll().stream().findFirst().ifPresent(prof -> {
            if (!Boolean.TRUE.equals(prof.getAtivo())) {
                prof.setAtivo(Boolean.TRUE);
                gerenteRepository.save(prof);
            }
        });
    }
}