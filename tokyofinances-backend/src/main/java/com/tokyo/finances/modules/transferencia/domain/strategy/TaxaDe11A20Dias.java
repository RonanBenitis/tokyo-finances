package com.tokyo.finances.modules.transferencia.domain.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TaxaDe11A20Dias implements CalculoTaxaStrategy {

    @Override
    public boolean seAplica(int dias) {
        return dias >= 11 && dias <= 20;
    }

    @Override
    public BigDecimal calcular(BigDecimal valor) {
        return valor.multiply(new BigDecimal("0.082"));
    }
}
