package com.bazar.proyectoBazar.service;

import com.bazar.proyectoBazar.model.Cliente;
import com.bazar.proyectoBazar.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {
    @Autowired
    IClienteRepository clienteRepo;

    @Override
    public void saveCliente(Cliente cliente) {
        clienteRepo.save(cliente);
    }

    @Override
    public List<Cliente> getClientes() {
        List<Cliente> listaClientes = clienteRepo.findAll();
        return listaClientes;
    }

    @Override
    public Cliente findCliente(Long id_cliente) {
        return clienteRepo.findById(id_cliente).orElse(null);
    }

    @Override
    public void deleteCliente(Long id_cliente) {
        clienteRepo.deleteById(id_cliente);
    }

    @Override
    public void editCliente(Long id_cliente_original, Long id_cliente_new, String nombre, String apellido, String dni) {
        Cliente cliente = this.findCliente(id_cliente_original);

        cliente.setId_cliente(id_cliente_new);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDni(dni);

        this.saveCliente(cliente);
    }
}
