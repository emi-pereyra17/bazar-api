package com.bazar.proyectoBazar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long cod_producto;

    @NotBlank(message = "El campo no puede estar vacío o ser solo espacios")
    @Size(min = 3, max = 25, message = "El tamaño debe estar entre 3 y 25 caracteres")
    private String nombre;

    @NotBlank(message = "El campo no puede estar vacío o ser solo espacios")
    @Size(min = 2, max = 25, message = "El tamaño debe estar entre 2 y 25 caracteres")
    private String marca;

    @NotNull(message = "El campo no puede ser nulo")
    @Min(value = 1, message = "El costo mínimo es 1")
    private Double costo;

    @Min(value = 1, message = "La cantidad disponible mínima es 1")
    private int cant_disponible;

    public Producto() {
    }

    public Producto(Long cod_producto, String nombre, String marca, Double costo, int cant_disponible) {
        this.cod_producto = cod_producto;
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cant_disponible = cant_disponible;
    }
}
