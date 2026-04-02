package com.tokyo.finances.modules.transferencia.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransferenciaNaoPermitidaException extends RuntimeException {
    public TransferenciaNaoPermitidaException(String s) {
        super(s);
    }
}
