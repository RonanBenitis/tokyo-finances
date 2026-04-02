package com.tokyo.finances.modules.transferencia.application;

import com.tokyo.finances.modules.conta.application.ContaMockAdapter;
import com.tokyo.finances.modules.transferencia.application.dto.AgendamentoRequest;
import com.tokyo.finances.modules.transferencia.domain.model.agendamento.AgendamentoTransferencia;
import com.tokyo.finances.modules.transferencia.domain.model.agendamento.ContaBancaria;
import com.tokyo.finances.modules.transferencia.domain.port.input.AgendarTransferenciaUseCase;
import com.tokyo.finances.modules.transferencia.domain.port.input.ConsultarExtratoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/transferencias")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AgendamentoController {
    private final AgendarTransferenciaUseCase useCase;
    private final ConsultarExtratoUseCase extratoUseCase;
    private final ContaMockAdapter contaController;

    @PostMapping
    public ResponseEntity<AgendamentoTransferencia> agendar(@RequestBody @Valid AgendamentoRequest request) {
        ContaBancaria origem = contaController.buscar(request.getContaOrigem());
        ContaBancaria destino = contaController.buscar(request.getContaDestino());

        AgendamentoTransferencia dominio = new AgendamentoTransferencia(
                origem, destino, request.getValor(), request.getDataTransferencia()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(useCase.executar(dominio));
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoTransferencia>> extrato() {
        return ResponseEntity.ok(extratoUseCase.executar());
    }
}