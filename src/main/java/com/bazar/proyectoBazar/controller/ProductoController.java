package com.bazar.proyectoBazar.controller;

import com.bazar.proyectoBazar.model.Cliente;
import com.bazar.proyectoBazar.model.Producto;
import com.bazar.proyectoBazar.service.IProductoService;
import com.bazar.proyectoBazar.service.IVentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService prodServ;


    @PostMapping("/create")
    @Operation(summary = "Crear un producto", description = "Crea un nuevo producto en el sistema proporcionando los datos necesarios. **No es necesario enviar un ID, ya que se genera automáticamente.**")
    public String saveProducto(@Valid @RequestBody Producto prod) {
        prodServ.saveProducto(prod);
        return "Producto creado correctamente";
    }

    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Retorna un listado con todos los productos")
    public List<Producto> getProductos() {
        return prodServ.getProductos();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Obtener producto por ID", description = "Retorna un producto según el ID proporcionado")
    public Producto getProducto(@PathVariable Long id) {
        return prodServ.findProducto(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Eliminar producto",
            description = "Elimina un producto del sistema usando su ID." +
                    "**Importante**: Para eliminar un producto, primero debes eliminar todas las ventas asociadas a él."
    )
    public String deleteProducto(@PathVariable Long id) {
        prodServ.deleteProducto(id);
        return "El producto se ha eliminado correctamente";
    }

    @PutMapping("/edit/{id_producto_original}")
    @Operation(
            summary = "Editar producto",
            description = "Permite actualizar los datos de un producto existente. Se debe proporcionar el ID del producto original y opcionalmente se pueden cambiar otros datos como ID, nombre, marca, costo y cant_disponible."
    )
    public Producto editProducto(@Parameter(description = "ID del producto original que se desea editar") @PathVariable Long id_producto_original,
                                 @Parameter(description = "Nuevo ID del producto (opcional)") @RequestParam(required = false, name = "id") Long id_producto_new,
                                 @Parameter(description = "Nuevo nombre del producto (opcional)") @RequestParam(required = false, name = "nombre") String nombre,
                                 @Parameter(description = "Nueva marca del producto (opcional)") @RequestParam(required = false, name = "marca") String marca,
                                 @Parameter(description = "Nuevo costo del producto (opcional)") @RequestParam(required = false, name = "costo") Double costo,
                                 @Parameter(description = "Nueva cantidad disponible del producto (opcional)") @RequestParam(required = false, name = "cant_disponible") int cant_disponible) {

        prodServ.editProducto(id_producto_original, id_producto_new, nombre, marca, costo, cant_disponible);

        Producto prod = prodServ.findProducto(id_producto_new);
        return prod;
    }

    @GetMapping("/lowstock")
    @Operation(
            summary = "Obtener productos con bajo stock",
            description = "Devuelve una lista de productos cuya cantidad disponible es menor a 5."
    )
    public List<Producto> controlStock() {
        List<Producto> listaProductos = this.getProductos();
        List<Producto> lowStockProductos = new ArrayList<>();
        for (Producto prod : listaProductos) {
            if (prod.getCant_disponible() < 5) {
                lowStockProductos.add(prod);
            }
        }
        return lowStockProductos;
    }

}
