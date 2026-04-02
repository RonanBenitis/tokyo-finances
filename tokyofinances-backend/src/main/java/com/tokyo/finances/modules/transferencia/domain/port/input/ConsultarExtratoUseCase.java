package com.tokyo.finances.modules.transferencia.domain.port.input;

import com.tokyo.finances.modules.transferencia.domain.model.agendamento.AgendamentoTransferencia;

import java.util.List;

public interface ConsultarExtratoUseCase {
    List<AgendamentoTransferencia> executar();
}
