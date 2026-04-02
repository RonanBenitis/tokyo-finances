package com.tokyo.finances.modules.transferencia.domain.port.output;


import com.tokyo.finances.modules.transferencia.domain.model.agendamento.AgendamentoTransferencia;

import java.util.List;

public interface AgendamentoRepositoryPort {
    AgendamentoTransferencia salva(AgendamentoTransferencia agendamento);
    List<AgendamentoTransferencia> buscaTodos();
}
