package com.bazar.proyectoBazar.service;

import com.bazar.proyectoBazar.model.Cliente;
import com.bazar.proyectoBazar.model.Producto;
import com.bazar.proyectoBazar.model.Venta;
import com.bazar.proyectoBazar.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class VentaService implements IVentaService {
    @Autowired
    IVentaRepository ventaRepo;

    @Override
    public void saveVenta(Venta venta) {
        ventaRepo.save(venta);
    }

    @Override
    public List<Venta> getVentas() {
        List<Venta> listaVentas = ventaRepo.findAll();
        return listaVentas;
    }

    @Override
    public Venta findVenta(Long id_venta) {
        return ventaRepo.findById(id_venta).orElse(null);
    }

    @Override
    @Transactional
    public void deleteVenta(Long id_venta) {
        Venta venta = ventaRepo.findById(id_venta).orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        venta.getListaProductos().clear();
        ventaRepo.save(venta);


        ventaRepo.delete(venta);
    }

    @Override
    public void editVenta(Long id_venta_original, Long id_venta_new, LocalDate fecha_venta, Double total, List<Producto> listaProductos, Cliente unCliente) {
        Venta venta = this.findVenta(id_venta_original);

        venta.setCod_venta(id_venta_new);
        venta.setFecha_venta(fecha_venta);
        venta.setTotal(total);
        venta.setListaProductos(listaProductos);
        venta.setUnCliente(unCliente);

        this.saveVenta(venta);

    }

    @Override
    public void editVenta(Venta venta) {
        this.saveVenta(venta);
    }

    @Override
    public boolean existsById(Long id) {
        return ventaRepo.existsById(id);
    }

}
