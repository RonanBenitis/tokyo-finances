package com.tokyo.finances.modules.conta.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/contas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ContaController {
    private final ContaMockAdapter contaMock;

    @GetMapping
    public ResponseEntity<Map<String, BigDecimal>> listarContasDisponiveis() {
        return ResponseEntity.ok(contaMock.listarTodas());
    }
}