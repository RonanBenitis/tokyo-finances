package com.tokyo.finances.modules.transferencia.domain.model.agendamento;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class ContaBancaria {
    private final String codigo;
    private final BigDecimal saldo;
}
