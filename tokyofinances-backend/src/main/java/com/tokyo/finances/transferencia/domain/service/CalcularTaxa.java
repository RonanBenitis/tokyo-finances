package com.tokyo.finances.transferencia.domain.service;

import com.tokyo.finances.transferencia.domain.exception.TransferenciaNaoPermitidaException;
import com.tokyo.finances.transferencia.domain.strategy.CalculoTaxaStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalcularTaxa {

    private final List<CalculoTaxaStrategy> estrategias;

    public BigDecimal calcular(BigDecimal quantia, int dias) {
        return estrategias.stream()
                .filter(e -> e.seAplica(dias))
                .findFirst()
                .map(e -> e.calcular(quantia))
                .orElseThrow(() -> new TransferenciaNaoPermitidaException("Não há taxa aplicável para este período."));
    }
}
