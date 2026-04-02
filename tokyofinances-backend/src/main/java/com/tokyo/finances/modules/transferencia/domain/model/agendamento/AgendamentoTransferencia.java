package com.tokyo.finances.modules.transferencia.domain.model.agendamento;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class AgendamentoTransferencia {
    private Long id;
    private ContaBancaria contaOrigem;
    private ContaBancaria contaDestino;
    private BigDecimal valor;
    private BigDecimal taxa;
    private LocalDate dataTransferencia;
    private LocalDate dataAgendamento;

    public  AgendamentoTransferencia(){}

    public AgendamentoTransferencia(ContaBancaria contaOrigem, ContaBancaria contaDestino, BigDecimal valor, LocalDate dataTransferencia) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.dataTransferencia = dataTransferencia;
        this.dataAgendamento = LocalDate.now();
    }
}