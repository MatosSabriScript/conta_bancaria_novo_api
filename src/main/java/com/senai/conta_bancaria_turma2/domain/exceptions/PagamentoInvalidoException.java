package com.senai.conta_bancaria_turma2.domain.exceptions;

public class PagamentoInvalidoException extends RuntimeException {
    public PagamentoInvalidoException()
    {
      super("Pagamento inválido");
    }
}
