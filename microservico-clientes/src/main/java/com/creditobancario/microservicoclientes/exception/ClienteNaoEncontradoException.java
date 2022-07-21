package com.creditobancario.microservicoclientes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClienteNaoEncontradoException extends RuntimeException {

    public ClienteNaoEncontradoException(String msg){
        super(msg);
    }
}
