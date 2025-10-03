package com.senai.conta_bancaria_turma2.domain.exceptions;

public class TransferirParaMesmaContaException extends RuntimeException {
    public TransferirParaMesmaContaException() {
        super("Não é possivel transferir para a mesma conta");
    }
}
