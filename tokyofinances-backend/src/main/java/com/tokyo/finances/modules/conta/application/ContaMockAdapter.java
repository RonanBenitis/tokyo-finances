package com.tokyo.finances.modules.conta.application;

import com.tokyo.finances.modules.transferencia.domain.model.agendamento.ContaBancaria;

import java.math.BigDecimal;
import java.util.Map;

public class ContaMockAdapter {
    private static final Map<String, BigDecimal> BANCO_MOCK = Map.of(
            "1234567890", new BigDecimal("1000.00"),
            "0987654321", new BigDecimal("500.00"),
            "1111111111", new BigDecimal("2500.00"),
            "2222222222", new BigDecimal("3000.00"),
            "3333333333", new BigDecimal("4000.00"),
            "8888888888", new BigDecimal("5000.00"),
            "9999999999", new BigDecimal("6000.00"),
            "5555555555", new BigDecimal("1000.00")
    );

    public ContaBancaria buscar(String codigo) {
        if (!BANCO_MOCK.containsKey(codigo)) {
            throw new RuntimeException(
                    "Conta " + codigo + " não encontrada. Contas disponíveis: " + BANCO_MOCK.keySet()
            );
        }
        return new ContaBancaria(codigo, BANCO_MOCK.get(codigo));
    }

    public Map<String, BigDecimal> listarTodas() {
        return BANCO_MOCK;
    }
}
