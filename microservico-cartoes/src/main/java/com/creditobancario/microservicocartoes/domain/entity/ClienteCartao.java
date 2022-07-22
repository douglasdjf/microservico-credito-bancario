package com.creditobancario.microservicocartoes.domain.entity;

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
public class ClienteCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String cpf;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_cartao")
    private Cartao cartao;


    private BigDecimal limite;
}
