package com.tokyo.finances.modules.transferencia.domain.service;

import com.tokyo.finances.modules.transferencia.domain.exception.TransferenciaNaoPermitidaException;
import com.tokyo.finances.modules.transferencia.domain.model.agendamento.AgendamentoTransferencia;
import com.tokyo.finances.modules.transferencia.domain.model.agendamento.ContaBancaria;
import com.tokyo.finances.modules.transferencia.domain.port.input.AgendarTransferenciaUseCase;
import com.tokyo.finances.modules.transferencia.domain.port.output.AgendamentoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class AgendarTransferencia implements AgendarTransferenciaUseCase {
    private final AgendamentoRepositoryPort repositoryPort;
    private final CalcularTaxa calcularTaxa;

    @Transactional
    public AgendamentoTransferencia executar(AgendamentoTransferencia agendamento) {
        int dias = (int) ChronoUnit.DAYS.between(agendamento.getDataAgendamento(), agendamento.getDataTransferencia());

        BigDecimal taxa = calcularTaxa.calcular(agendamento.getValor(), dias);
        agendamento.setTaxa(taxa);

        BigDecimal totalNecessario = agendamento.getValor().add(taxa);
        if (agendamento.getContaOrigem().getSaldo().compareTo(totalNecessario) < 0) {
            throw new TransferenciaNaoPermitidaException("Saldo insuficiente na conta de origem");
        }

        return repositoryPort.salva(agendamento);
    }
}
