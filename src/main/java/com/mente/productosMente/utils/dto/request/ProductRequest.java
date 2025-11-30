package com.mente.productosMente.utils.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder // ordena la manera de crear el objeto
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @Size(min = 0, max = 40, message = "El nombre supera la cantidad de caracteres permitidos")
    @NotBlank(message = "El nombre del producto es requerido")
    private String name;
    @NotNull(message = "El precio del producto es requerido")
    @Positive
    private double price;

}
