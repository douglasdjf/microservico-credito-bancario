package com.creditobancario.microservicocartoes.dto;

import com.creditobancario.microservicocartoes.domain.entity.Cartao;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteCartaoDTO {

    private Long id;

    private String cpf;

    private CartaoDTO cartao;

    private BigDecimal limite;
}
