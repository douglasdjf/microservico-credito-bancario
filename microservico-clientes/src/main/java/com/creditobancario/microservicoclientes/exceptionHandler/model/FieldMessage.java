package com.creditobancario.microservicoclientes.exceptionHandler.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FieldMessage {

    private String fieldName;
    private String message;

}
