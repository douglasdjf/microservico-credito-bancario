package com.creditobancario.microservicoavaliadorcredito.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DadosAvaliacaoDTO {

    private String cpf;
    private Long renda;
}
