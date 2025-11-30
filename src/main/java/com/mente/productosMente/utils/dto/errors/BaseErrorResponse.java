package com.mente.productosMente.utils.dto.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
// SuperBuilder crea un constructor con el super de Serializable
@SuperBuilder
// esta clase es para representar errores en la API
public class BaseErrorResponse implements Serializable {
    private String status;
    private Integer code;
}
