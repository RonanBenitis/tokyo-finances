package com.tokyo.finances.modules.transferencia.domain.port.input;

import com.tokyo.finances.modules.transferencia.domain.model.agendamento.AgendamentoTransferencia;

public interface AgendarTransferenciaUseCase {
    AgendamentoTransferencia executar(AgendamentoTransferencia agendamento);
}
