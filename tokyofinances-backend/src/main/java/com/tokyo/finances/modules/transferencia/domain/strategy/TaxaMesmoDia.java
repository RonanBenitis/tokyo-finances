package com.tokyo.finances.modules.transferencia.domain.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TaxaMesmoDia implements CalculoTaxaStrategy{
    @Override
    public boolean seAplica(int dias) { return dias == 0; }

    @Override
    public BigDecimal calcular(BigDecimal valor) {
        return valor.multiply(new BigDecimal("0.025")).add(new BigDecimal("3.00"));
    }
}
