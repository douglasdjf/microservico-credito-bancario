package com.creditobancario.microservicoavaliadorcredito.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartaoAprovadoDTO {

    private String cartao;
    private String bandeira;
    private BigDecimal limiteAprovado;
}
