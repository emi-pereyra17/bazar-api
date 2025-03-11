package com.bazar.proyectoBazar.service;

import com.bazar.proyectoBazar.model.Cliente;
import com.bazar.proyectoBazar.model.Producto;
import com.bazar.proyectoBazar.model.Venta;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {

    public void saveVenta(Venta venta);

    public List<Venta> getVentas();

    public Venta findVenta(Long id_venta);

    public void deleteVenta(Long id_venta);

    public void editVenta(Long id_venta_original, Long id_venta_new, LocalDate fecha_venta, Double total, List<Producto> listaProductos, Cliente unCliente);

    void editVenta(Venta venta);

    public boolean existsById(Long id);

}
