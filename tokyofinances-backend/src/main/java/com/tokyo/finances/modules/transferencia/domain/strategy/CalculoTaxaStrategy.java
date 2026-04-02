package com.tokyo.finances.modules.transferencia.domain.strategy;

import java.math.BigDecimal;

public interface CalculoTaxaStrategy {
    boolean seAplica(int dias);
    BigDecimal calcular(BigDecimal valor);
}
