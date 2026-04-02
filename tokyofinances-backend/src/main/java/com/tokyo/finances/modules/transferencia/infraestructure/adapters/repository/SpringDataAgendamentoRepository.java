package com.tokyo.finances.modules.transferencia.infraestructure.adapters.repository;

import com.tokyo.finances.modules.transferencia.infraestructure.adapters.repository.entity.AgendamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataAgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {
}
