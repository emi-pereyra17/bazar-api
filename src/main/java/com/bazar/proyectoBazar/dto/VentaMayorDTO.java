package com.bazar.proyectoBazar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentaMayorDTO {

    private Long codigo_venta;
    private Double monto_total;
    private int cant_productos;
    private String nombre_cliente;
    private String apellido_cliente;

    public VentaMayorDTO() {
    }

    public VentaMayorDTO(Long codigo_venta, Double monto_total, int cant_productos, String nombre_cliente, String apellido_cliente) {
        this.codigo_venta = codigo_venta;
        this.monto_total = monto_total;
        this.cant_productos = cant_productos;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
    }
}
