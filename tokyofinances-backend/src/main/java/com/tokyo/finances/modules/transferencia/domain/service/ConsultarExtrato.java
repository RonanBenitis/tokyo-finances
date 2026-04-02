package com.tokyo.finances.modules.transferencia.domain.service;

import com.tokyo.finances.modules.transferencia.domain.model.agendamento.AgendamentoTransferencia;
import com.tokyo.finances.modules.transferencia.domain.port.input.ConsultarExtratoUseCase;
import com.tokyo.finances.modules.transferencia.domain.port.output.AgendamentoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultarExtrato implements ConsultarExtratoUseCase {
    private final AgendamentoRepositoryPort repositoryPort;

    @Override
    public List<AgendamentoTransferencia> executar() {
        return repositoryPort.buscaTodos();
    }
}
