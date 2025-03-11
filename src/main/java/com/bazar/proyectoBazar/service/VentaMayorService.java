package com.bazar.proyectoBazar.service;

import com.bazar.proyectoBazar.dto.VentaMayorDTO;
import com.bazar.proyectoBazar.model.Venta;
import com.bazar.proyectoBazar.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaMayorService implements IVentaMayorService {

    @Autowired
    IVentaRepository ventaRepo;

    @Override
    public VentaMayorDTO ventaMayor() {
        List<Venta> getVentas = ventaRepo.findAll();

        if (getVentas.isEmpty()) {
            return null;
        }

        VentaMayorDTO ventaM = new VentaMayorDTO();
        Venta ventaMayor = null;
        Double montoMayor = 0.0;

        for (Venta venta : getVentas) {
            if (venta.getTotal() > montoMayor) {
                montoMayor = venta.getTotal();
                ventaMayor = venta;
            }
        }

        if (ventaMayor != null) {
            ventaM.setCodigo_venta(ventaMayor.getCod_venta());
            ventaM.setMonto_total(ventaMayor.getTotal());
            ventaM.setCant_productos(ventaMayor.getListaProductos().size());
            ventaM.setNombre_cliente(ventaMayor.getUnCliente().getNombre());
            ventaM.setApellido_cliente(ventaMayor.getUnCliente().getApellido());
        }

        return ventaM;
    }
}
