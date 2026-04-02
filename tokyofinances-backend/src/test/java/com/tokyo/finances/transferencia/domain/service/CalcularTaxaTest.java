package com.tokyo.finances.transferencia.domain.service;

import com.tokyo.finances.transferencia.domain.exception.TransferenciaNaoPermitidaException;
import com.tokyo.finances.transferencia.domain.strategy.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
class CalcularTaxaTest {

    @InjectMocks
    private CalcularTaxa calculoTaxa;

    @BeforeEach
    void setup() {
        List<CalculoTaxaStrategy> estrategias = Arrays.asList(
                new TaxaMesmoDia(),
                new TaxaDe1A10Dias(),
                new TaxaDe11A20Dias(),
                new TaxaDe21A30Dias(),
                new TaxaDe31A40Dias(),
                new TaxaDe41A50Dias()
        );
        calculoTaxa = new CalcularTaxa(estrategias);
    }

    @ParameterizedTest
    @CsvSource({
        "0, 100.00, 5.50",    // D0: 2.5% de 100 + 3.00
        "5, 100.00, 12.00",   // D1-D10: Fixo 12.00
        "10, 100.00, 12.00",  // Limite D10
        "11, 100.00, 8.20",   // D11-D20: 8.2%
        "21, 100.00, 6.90",   // D21-D30: 6.9%
        "31, 100.00, 4.70",   // D31-D40: 4.7%
        "41, 100.00, 1.70",   // D41-D50: 1.7%
        "50, 100.00, 1.70",   // Limite D50
    })
    @DisplayName("Deve calcular corretamente a taxa conforme regras de transferencia")
    void deveCalcularTaxaCorretamente(int dias, BigDecimal quantia, BigDecimal taxaEsperada) {
        // ARRANGE E ACT
        BigDecimal resultado = calculoTaxa.calcular(quantia, dias);

        // ASSERT
        assertEquals(0, taxaEsperada.compareTo(resultado), "Erro no cálculo para " + dias + " dias");
    }

    @Test
    @DisplayName("Deve lançar erro de taxa não aplicada")
    void deveLancarErroDeTaxaNaoAplicada() {
        // ARANGE
        int dias = 51;
        BigDecimal quantia = new BigDecimal("100");

        // ACT E ASSERT
        assertThrows(TransferenciaNaoPermitidaException.class,
                () -> calculoTaxa.calcular(quantia, dias));
    }
}