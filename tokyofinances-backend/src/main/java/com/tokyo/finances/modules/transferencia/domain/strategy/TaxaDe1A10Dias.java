package com.tokyo.finances.modules.transferencia.domain.strategy;

import java.math.BigDecimal;

public class TaxaDe1A10Dias implements CalculoTaxaStrategy {

    @Override
    public boolean seAplica(int dias) {
        return dias >= 1 && dias <= 10;
    }

    @Override
    public BigDecimal calcular(BigDecimal valor) {
        return new BigDecimal("12.00");
    }
}
