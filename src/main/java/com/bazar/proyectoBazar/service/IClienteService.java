package com.bazar.proyectoBazar.service;

import com.bazar.proyectoBazar.model.Cliente;
import com.bazar.proyectoBazar.model.Producto;

import java.util.List;

public interface IClienteService {

    public void saveCliente(Cliente cliente);

    public List<Cliente> getClientes();

    public Cliente findCliente(Long id_cliente);

    public void deleteCliente(Long id_cliente);

    public void editCliente(Long id_cliente_original, Long id_cliente_new, String nombre, String apellido, String dni);

}
