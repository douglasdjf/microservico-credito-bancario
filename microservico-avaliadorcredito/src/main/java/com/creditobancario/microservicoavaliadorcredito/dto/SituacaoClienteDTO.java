package com.creditobancario.microservicoavaliadorcredito.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SituacaoClienteDTO {

    private DadosClienteDTO cliente;
    private List<ClienteCartaoDTO> cartoes;

}
