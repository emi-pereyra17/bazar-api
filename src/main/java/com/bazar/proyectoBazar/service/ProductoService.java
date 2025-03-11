package com.bazar.proyectoBazar.service;

import com.bazar.proyectoBazar.model.Producto;
import com.bazar.proyectoBazar.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository prodRepo;

    @Override
    public void saveProducto(Producto prod) {
        prodRepo.save(prod);
    }

    @Override
    public List<Producto> getProductos() {
        List<Producto> listaProductos = prodRepo.findAll();
        return listaProductos;
    }

    @Override
    public Producto findProducto(Long id_producto) {
        return prodRepo.findById(id_producto).orElse(null);
    }

    @Override
    public void deleteProducto(Long id_producto) {
        prodRepo.deleteById(id_producto);
    }

    @Override
    public void editProducto(Long id_producto_original, Long id_producto_new, String nombre, String marca, Double costo, int cant_disponible) {
        Producto prod = this.findProducto(id_producto_original);

        prod.setCod_producto(id_producto_new);
        prod.setNombre(nombre);
        prod.setMarca(marca);
        prod.setCosto(costo);
        prod.setCant_disponible(cant_disponible);

        this.saveProducto(prod);
    }
}
