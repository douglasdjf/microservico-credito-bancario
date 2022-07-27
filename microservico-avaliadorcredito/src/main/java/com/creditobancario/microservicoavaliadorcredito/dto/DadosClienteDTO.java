package com.creditobancario.microservicoavaliadorcredito.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DadosClienteDTO {

    private Long id;
    private String nome;
    private Integer idade;
}
