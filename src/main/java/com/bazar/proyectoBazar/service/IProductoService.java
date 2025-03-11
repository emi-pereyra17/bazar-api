package com.bazar.proyectoBazar.service;

import com.bazar.proyectoBazar.model.Producto;

import java.util.List;

public interface IProductoService {

    public void saveProducto(Producto prod);

    public List<Producto> getProductos();

    public Producto findProducto(Long id_producto);

    public void deleteProducto(Long id_producto);

    public void editProducto(Long id_producto_original, Long id_producto_new, String nombre, String marca, Double costo, int cant_disponible);

}
