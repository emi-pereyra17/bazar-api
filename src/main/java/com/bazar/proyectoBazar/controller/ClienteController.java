package com.bazar.proyectoBazar.controller;

import com.bazar.proyectoBazar.model.Cliente;
import com.bazar.proyectoBazar.model.Producto;
import com.bazar.proyectoBazar.repository.IClienteRepository;
import com.bazar.proyectoBazar.service.IClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    IClienteService clienteServ;

    @PostMapping("/create")
    @Operation(summary = "Crear un cliente", description = "Crea un nuevo cliente en el sistema proporcionando los datos necesarios. **No es necesario enviar un ID, ya que se genera automáticamente.**")
    public String saveCliente(@Valid @RequestBody Cliente cliente) {
        System.out.println(cliente);
        clienteServ.saveCliente(cliente);
        return "Cliente creado correctamente";
    }

    @GetMapping
    @Operation(summary = "Obtener todos los clientes", description = "Retorna un listado con todos los clientes")
    public List<Cliente> getClientes() {
        return clienteServ.getClientes();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Obtener cliente por ID", description = "Retorna un cliente según el ID proporcionado")
    public Cliente getCliente(@PathVariable Long id) {
        return clienteServ.findCliente(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Eliminar cliente",
            description = "Elimina un cliente del sistema usando su ID." +
                    "**Importante**: Para eliminar un cliente, primero debes eliminar todas las ventas asociadas a él."
    )
    public String deleteCliente(@PathVariable Long id) {
        clienteServ.deleteCliente(id);
        return "El cliente se ha eliminado correctamente";
    }


    @PutMapping("/edit/{id_cliente_original}")
    @Operation(
            summary = "Editar cliente",
            description = "Permite actualizar los datos de un cliente existente. Se debe proporcionar el ID del cliente original y opcionalmente se pueden cambiar otros datos como ID, nombre, apellido y DNI."
    )
    public Cliente editCliente(@Parameter(description = "ID del cliente original que se desea editar") @PathVariable Long id_cliente_original,
                               @Parameter(description = "Nuevo ID del cliente (opcional)") @RequestParam(required = false, name = "id") Long id_cliente_new,
                               @Parameter(description = "Nuevo nombre del cliente (opcional)") @RequestParam(required = false, name = "nombre") String nombre,
                               @Parameter(description = "Nuevo apellido del cliente (opcional)") @RequestParam(required = false, name = "apellido") String apellido,
                               @Parameter(description = "Nuevo DNI del cliente (opcional)") @RequestParam(required = false, name = "dni") String dni) {

        clienteServ.editCliente(id_cliente_original, id_cliente_new, nombre, apellido, dni);

        Cliente cliente = clienteServ.findCliente(id_cliente_new);
        return cliente;
    }

}
