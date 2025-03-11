package com.bazar.proyectoBazar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_cliente;

    @NotBlank(message = "El campo no puede estar vacío o ser solo espacios")
    @Size(min = 3, max = 25, message = "El tamaño debe estar entre 3 y 25 caracteres")
    private String nombre;

    @NotBlank(message = "El campo no puede estar vacío o ser solo espacios")
    @Size(min = 3, max = 25, message = "El tamaño debe estar entre 3 y 25 caracteres")
    private String apellido;

    @NotBlank(message = "El campo no puede estar vacío o ser solo espacios")
    @Size(min = 8, max = 8, message = "El tamaño del DNI debe ser de 8 caracteres")
    @Pattern(regexp = "^[0-9]+$", message = "El DNI debe ser un número válido")
    private String dni;

    public Cliente() {
    }

    public Cliente(Long id_cliente, String nombre, String apellido, String dni) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }
}
