package com.senai.conta_bancaria_turma2.domain.exceptions;

public class ValoresNegativosException extends RuntimeException {
    public ValoresNegativosException(String operacao) {
        super("Não é possivel realizar a operação de"+operacao+"com valores negativos");
    }
}
