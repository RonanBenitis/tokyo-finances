package com.tokyo.finances.modules.transferencia.domain.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TaxaDe31A40Dias implements CalculoTaxaStrategy {

    @Override
    public boolean seAplica(int dias) {
        return dias >= 31 && dias <= 40;
    }

    @Override
    public BigDecimal calcular(BigDecimal valor) {
        return valor.multiply(new BigDecimal("0.047"));
    }
}
