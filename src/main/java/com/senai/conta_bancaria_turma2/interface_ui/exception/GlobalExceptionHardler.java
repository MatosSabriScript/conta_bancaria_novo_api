
package com.senai.conta_bancaria_turma2.interface_ui.exception;

import com.senai.conta_bancaria_turma2.domain.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.senai.conta_bancaria_turma2.interface_ui.exception.ProblemDetailUtils.buildProblem;

@RestControllerAdvice
public class GlobalExceptionHardler {
    @ExceptionHandler(ValoresNegativosException.class)
    public ProblemDetail handleValoresNegativo(ValoresNegativosException ex, HttpServletRequest request) {
        return ProblemDetailUtils.buildProblem(
                HttpStatus.BAD_REQUEST,
                "Valores negativos não são permitidos.",
                ex.getMessage(),
                request.getRequestURI()
        );

    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ProblemDetail handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, HttpServletRequest request) {
        return ProblemDetailUtils.buildProblem(
                HttpStatus.NOT_FOUND,
                "Entidade não encontrada.",
                ex.getMessage(),
                request.getRequestURI()
        );
    }


    @ExceptionHandler(ContaMesmoTipoException.class)
    public ProblemDetail handleContaMesmoTipo(ContaMesmoTipoException ex, HttpServletRequest request) {
        return ProblemDetailUtils.buildProblem(
                HttpStatus.CONFLICT,
                "Conta do mesmo tipo já existe para este cliente.",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(RendimentoInvalidoException.class)
    public ResponseEntity<String> handleRendimentoInvalidoException(RendimentoInvalidoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<String> handleSaldoInsuficienteException(SaldoInsuficienteException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.PAYMENT_REQUIRED);
    }
    @ExceptionHandler(TipoDeContaInativaException.class)
    public ResponseEntity<String> handleTipoDeContaInativaException(TipoDeContaInativaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TransferirParaMesmaContaException.class)
    public ResponseEntity<String> handleTransferirParaMesmaContaException(TransferirParaMesmaContaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("Ocorreu um erro inesperado: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
