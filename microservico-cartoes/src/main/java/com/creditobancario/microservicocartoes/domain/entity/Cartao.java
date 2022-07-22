package com.creditobancario.microservicocartoes.domain.entity;


import com.creditobancario.microservicocartoes.domain.entity.enums.BandeiraCartao;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeira;

    @NotNull
    private BigDecimal renda;

    @NotNull
    private BigDecimal limiteBasico;
}
