package com.creditobancario.microservicocartoes.dto;

import com.creditobancario.microservicocartoes.domain.entity.enums.BandeiraCartao;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CartaoDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    private BandeiraCartao bandeira;

    @NotNull
    private BigDecimal renda;

    @NotNull
    private BigDecimal limiteBasico;
}
