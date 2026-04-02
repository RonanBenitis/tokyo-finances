package com.tokyo.finances.transferencia.domain.strategy;

import java.math.BigDecimal;

public class TaxaDe41A50Dias implements CalculoTaxaStrategy {

    @Override
    public boolean seAplica(int dias) {
        return dias >= 41 && dias <= 50;
    }

    @Override
    public BigDecimal calcular(BigDecimal valor) {
        return valor.multiply(new BigDecimal("0.017"));
    }
}
