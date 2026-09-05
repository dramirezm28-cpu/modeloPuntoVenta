package com.punto.venta.controller;

import com.punto.venta.dto.ClienteDTO;
import com.punto.venta.entity.Cliente;
import com.punto.venta.service.ClienteService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService servicio;

    public ClienteController(ClienteService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<Cliente> listar() {
        return servicio.obtenerClientes();
    }

    @GetMapping("/activos")
    public List<ClienteDTO> mostrarActivos() {
        return servicio.mostrarActivos();
    }

    @GetMapping("/activos/filtro")
    public List<ClienteDTO> mostrarActivosFiltro(@RequestParam String filtro) {
        return servicio.mostrarActivosFiltro(filtro);
    }

    @GetMapping("/activos/filtro/top")
    public List<ClienteDTO> mostrarActivosFiltroTop(@RequestParam String filtro) {
        return servicio.mostrarActivosFiltroTop(filtro);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente registrar(@RequestBody ClienteDTO datosCliente) {
        return servicio.registrarCliente(datosCliente);
    }
}
