package com.tokyo.finances.modules.transferencia.application.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AgendamentoRequest {
    @NotBlank @Size(min = 10, max = 10)
    private String contaOrigem;
    @NotBlank @Size(min = 10, max = 10)
    private String contaDestino;
    @NotNull @Positive
    private BigDecimal valor;
    @NotNull
    private LocalDate dataTransferencia;
}