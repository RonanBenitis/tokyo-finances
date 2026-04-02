package com.tokyo.finances.modules.transferencia.infraestructure.adapters.repository.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "agendamentos")
@Data
public class AgendamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contaOrigem;
    private String contaDestino;
    private BigDecimal valor;
    private BigDecimal taxa;
    private LocalDate dataTransferencia;
    private LocalDate dataAgendamento;
}
