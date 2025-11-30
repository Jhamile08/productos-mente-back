package com.mente.productosMente.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "product") // nombre en la bd
@Data // crea los getters, setters, toString()
@AllArgsConstructor // crea constructor con argumentos
@NoArgsConstructor // crea constructor sin argumentos
public class Product {
    @Id // indica a la bd la primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el id autoincrementar
    private Long id;
    private String name;
    private double price;
}
