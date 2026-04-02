package com.tokyo.finances.modules.transferencia.infraestructure.adapters.repository.impl;

import com.tokyo.finances.modules.transferencia.domain.model.agendamento.AgendamentoTransferencia;
import com.tokyo.finances.modules.transferencia.domain.model.agendamento.ContaBancaria;
import com.tokyo.finances.modules.transferencia.domain.port.output.AgendamentoRepositoryPort;
import com.tokyo.finances.modules.transferencia.infraestructure.adapters.repository.SpringDataAgendamentoRepository;
import com.tokyo.finances.modules.transferencia.infraestructure.adapters.repository.entity.AgendamentoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AgendamentoH2Adapter implements AgendamentoRepositoryPort {
    private final SpringDataAgendamentoRepository jpaRepository;

    @Override
    public AgendamentoTransferencia salva(AgendamentoTransferencia domain) {
        AgendamentoEntity entity = toEntity(domain);
        AgendamentoEntity saved = jpaRepository.save(entity);
        domain.setId(saved.getId());
        return domain;
    }

    @Override
    public List<AgendamentoTransferencia> buscaTodos() {
        return jpaRepository.findAll().stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    // MAPPERS
    private AgendamentoEntity toEntity(AgendamentoTransferencia d) {
        AgendamentoEntity e = new AgendamentoEntity();
        e.setId(d.getId());
        e.setContaOrigem(d.getContaOrigem().getCodigo());
        e.setContaDestino(d.getContaDestino().getCodigo());
        e.setValor(d.getValor());
        e.setTaxa(d.getTaxa());
        e.setDataTransferencia(d.getDataTransferencia());
        e.setDataAgendamento(d.getDataAgendamento());
        return e;
    }

    private AgendamentoTransferencia fromEntity(AgendamentoEntity e) {
        AgendamentoTransferencia d = new AgendamentoTransferencia();
        d.setId(e.getId());
        d.setContaOrigem(new ContaBancaria(e.getContaOrigem(), BigDecimal.ZERO));
        d.setContaDestino(new ContaBancaria(e.getContaDestino(), BigDecimal.ZERO));
        d.setValor(e.getValor());
        d.setTaxa(e.getTaxa());
        d.setDataTransferencia(e.getDataTransferencia());
        d.setDataAgendamento(e.getDataAgendamento());
        return d;
    }
}