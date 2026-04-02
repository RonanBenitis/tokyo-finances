package com.tokyo.finances.modules.transferencia.application.globalHanlder;

import com.tokyo.finances.modules.transferencia.domain.exception.TransferenciaNaoPermitidaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TransferenciaNaoPermitidaException.class)
    public ResponseEntity<ErroResponse> handleTransferencia(TransferenciaNaoPermitidaException ex, HttpServletRequest request) {
        return montarResposta(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handleValidacao(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String mensagemDetalhe = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return montarResposta(HttpStatus.BAD_REQUEST, "Erro de validação -> " + mensagemDetalhe, request);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroResponse> handleRuntime(RuntimeException ex, HttpServletRequest request) {
        return montarResposta(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor", request);
    }

    private ResponseEntity<ErroResponse> montarResposta(HttpStatus status, String message, HttpServletRequest request) {
        ErroResponse erro = ErroResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .path(request.getRequestURI())
                .message(message)
                .build();
        return new ResponseEntity<>(erro, status);
    }
}