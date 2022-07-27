package com.creditobancario.microservicoavaliadorcredito.exception;

import lombok.Getter;

public class ErroComunicacaoMicroservicoException  extends  Exception{

    @Getter
    private Integer status;

    public ErroComunicacaoMicroservicoException(String msg,Integer status) {
        super(msg);
        this.status = status;
    }
}
