package com.tokyo.finances.transferencia.domain.strategy;

import java.math.BigDecimal;

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
