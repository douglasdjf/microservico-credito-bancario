package com.creditobancario.microservicoclientes.dto;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ClienteDTO {

    private Long id;

    @CPF
    @NotBlank(message = "Cpf não pode está em branco")
    private String cpf;

    @NotBlank(message = "nome não pode está em branco")
    private String nome;

    @NotNull(message = "idade não pode ser null")
    private Integer idade;
}
