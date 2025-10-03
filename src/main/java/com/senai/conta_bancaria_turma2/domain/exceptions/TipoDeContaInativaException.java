package com.senai.conta_bancaria_turma2.domain.exceptions;

public class TipoDeContaInativaException extends RuntimeException {
    public TipoDeContaInativaException() {
        super("Tipo de conta inválida.Os tipos válidos são: 'CORRENTE' ou ' POUPANCA'");
    }
}
