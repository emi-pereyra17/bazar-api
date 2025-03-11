package com.bazar.proyectoBazar.controller;

import com.bazar.proyectoBazar.dto.VentaMayorDTO;
import com.bazar.proyectoBazar.model.Cliente;
import com.bazar.proyectoBazar.model.Producto;
import com.bazar.proyectoBazar.model.Venta;
import com.bazar.proyectoBazar.service.IClienteService;
import com.bazar.proyectoBazar.service.IProductoService;
import com.bazar.proyectoBazar.service.IVentaMayorService;
import com.bazar.proyectoBazar.service.IVentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private IVentaService ventaServ;

    @Autowired
    private IClienteService clienteServ;

    @Autowired
    private IVentaMayorService ventaMayorServ;

    @Autowired
    private IProductoService prodServ;


    @PostMapping("/create")
    @Transactional
    @Operation(
            summary = "Registrar una nueva venta",
            description = "Crea una nueva venta. **No es necesario enviar un ID ni el monto, ya que se generan automáticamente.** Puedes enviar tanto los detalles completos del cliente y los productos, " +
                    "como solo sus respectivos IDs. Si envías solo los IDs, el sistema buscará los objetos correspondientes. " +
                    "Si se incluyen los detalles completos del cliente o producto, estos serán utilizados en la creación de la venta. " +
                    "La fecha debe ser proporcionada en el formato 'YYYY-MM-DD', por ejemplo: '2025-03-05'.")
    public ResponseEntity<String> saveVenta(@Valid @RequestBody Venta venta) {
        Double total = 0.0;
        List<Producto> productos = new ArrayList<>();


        if (venta.getUnCliente() == null) {
            return ResponseEntity.badRequest().body("Error: La venta debe estar asociada a un cliente.");
        }


        Cliente cliente = clienteServ.findCliente(venta.getUnCliente().getId_cliente());
        if (cliente == null) {
            return ResponseEntity.badRequest().body("Error: Cliente con ID " + venta.getUnCliente().getId_cliente() + " no encontrado.");
        }

        try {

            for (Producto prod : venta.getListaProductos()) {
                Producto productoRecuperado = prodServ.findProducto(prod.getCod_producto());

                if (productoRecuperado == null) {
                    return ResponseEntity.badRequest().body("Error: Producto con ID " + prod.getCod_producto() + " no encontrado.");
                }

                if (productoRecuperado.getCosto() == null) {
                    return ResponseEntity.badRequest().body("Error: Producto con ID " + prod.getCod_producto() + " tiene un costo no definido.");
                }

                if (productoRecuperado.getCant_disponible() < prod.getCant_disponible()) {
                    return ResponseEntity.badRequest().body("Error: No hay suficiente stock para el producto con ID " + prod.getCod_producto() + ".");
                }


                productoRecuperado.setCant_disponible(productoRecuperado.getCant_disponible() - 1);


                prodServ.saveProducto(productoRecuperado);


                total += productoRecuperado.getCosto(); // Sumamos al total


                productos.add(productoRecuperado);
            }


            venta.setUnCliente(cliente);
            venta.setListaProductos(productos);
            venta.setTotal(total);


            ventaServ.saveVenta(venta);

            return ResponseEntity.ok("Venta creada correctamente");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage());
        }
    }


    @GetMapping
    @Operation(summary = "Obtener todas las ventas", description = "Retorna un listado con todas las ventas")
    public List<Venta> getVentas() {
        return ventaServ.getVentas();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Obtener venta por ID", description = "Retorna una venta según el ID proporcionado")
    public Venta getVenta(@PathVariable Long id) {
        return ventaServ.findVenta(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Eliminar venta",
            description = "Elimina una venta del sistema usando su ID."
    )
    public String deleteVenta(@PathVariable Long id) {
        ventaServ.deleteVenta(id);
        return "La venta se ha eliminado correctamente";
    }

    @PutMapping("/edit")
    @Operation(
            summary = "Editar una venta",
            description = "Edita una venta existente. Puedes enviar los detalles completos del cliente y los productos, " +
                    "o solo sus IDs. Si se incluyen los detalles completos del cliente o los productos, estos serán utilizados para actualizar la venta. " +
                    "Si solo se envían los IDs, el sistema actualizará la venta con los objetos correspondientes según los IDs proporcionados."
    )
    public ResponseEntity<String> editVenta(@Valid @RequestBody Venta venta) {
        Double total = 0.0;
        List<Producto> productos = new ArrayList<>();

        try {

            for (Producto prod : venta.getListaProductos()) {
                Producto productoRecuperado = prodServ.findProducto(prod.getCod_producto());

                if (productoRecuperado == null) {
                    return ResponseEntity.badRequest().body("Error: Producto con ID " + prod.getCod_producto() + " no encontrado.");
                }

                if (productoRecuperado.getCosto() == null) {
                    return ResponseEntity.badRequest().body("Error: Producto con ID " + prod.getCod_producto() + " tiene un costo no definido.");
                }

                if (productoRecuperado.getCant_disponible() < prod.getCant_disponible()) {
                    return ResponseEntity.badRequest().body("Error: No hay suficiente stock para el producto con ID " + prod.getCod_producto() + ".");
                }


                prodServ.saveProducto(productoRecuperado);


                total += productoRecuperado.getCosto(); // Sumamos al total


                productos.add(productoRecuperado);
            }



            venta.setListaProductos(productos);
            venta.setTotal(total);


            ventaServ.editVenta(venta);

            return ResponseEntity.ok("Venta modificada correctamente");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage());
        }
    }

    @GetMapping("/productos/{codigo_venta}")
    @Operation(
            summary = "Obtener productos de una venta",
            description = "Devuelve la lista de productos asociados a una venta específica, identificada por su código."
    )
    public List<Producto> listaProductos(@PathVariable Long codigo_venta) {
        List<Producto> productosDeVenta = this.getVenta(codigo_venta).getListaProductos();

        return productosDeVenta;
    }

    @GetMapping("/{fecha_venta}")
    @Operation(
            summary = "Obtener cantidad y monto total de ventas en una fecha",
            description = "Devuelve la cantidad de ventas realizadas en una fecha específica y el monto total generado en ese día. " +
                    "La fecha debe ser proporcionada en el formato 'YYYY-MM-DD', por ejemplo: '2025-03-05'."

    )
    public String monto_cantidad(@PathVariable LocalDate fecha_venta) {
        List<Venta> totalVentas = this.getVentas();
        Double monto = 0.0;
        int cantTotalVentas = 0;
        for (Venta venta : totalVentas) {
            if (venta.getFecha_venta().equals(fecha_venta)) {
                monto += venta.getTotal();
                cantTotalVentas++;
            }
        }
        return "La cantidad de ventas en la fecha introducida es: " + cantTotalVentas + " y su monto es de: " + monto;
    }

    @GetMapping("/venta_mayor")
    @Operation(
            summary = "Obtener la venta con el monto más alto",
            description = "Devuelve la venta con el mayor monto registrado en el sistema."
    )
    public VentaMayorDTO getVentaMayor() {
        return ventaMayorServ.ventaMayor();
    }


}
