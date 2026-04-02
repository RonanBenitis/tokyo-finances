package com.tokyo.finances.modules.transferencia.application.globalHanlder;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErroResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;
    private String message;
}