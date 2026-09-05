package com.punto.venta.service;

import com.punto.venta.dto.ClienteDTO;
import com.punto.venta.entity.Cliente;
import com.punto.venta.repository.ClienteRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository repositorio;

    public ClienteService(ClienteRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Cliente> obtenerClientes() {
        return repositorio.findAll();
    }

    public List<ClienteDTO> mostrarActivos() {
        return repositorio.findByEstadoTrueOrderByIdClienteDesc().stream()
                .map(this::aDTO)
                .toList();
    }

    public List<ClienteDTO> mostrarActivosFiltro(String filtro) {
        return repositorio.findByEstadoTrueAndNombreContainingIgnoreCaseOrderByIdClienteDesc(filtro).stream()
                .map(this::aDTO)
                .toList();
    }

    public List<ClienteDTO> mostrarActivosFiltroTop(String filtro) {
        return repositorio.findTop3ByEstadoTrueAndNombreContainingIgnoreCaseOrderByIdClienteDesc(filtro).stream()
                .map(this::aDTO)
                .toList();
    }

    public Cliente registrarCliente(ClienteDTO datos) {
        Cliente nuevoCliente = construirCliente(datos);
        return repositorio.save(nuevoCliente);
    }

    private Cliente construirCliente(ClienteDTO datos) {
        Cliente cliente = new Cliente();
        cliente.setEstado(datos.getEstado());
        cliente.setNombre(datos.getNombre());
        cliente.setApellido(datos.getApellido());
        cliente.setEmail(datos.getEmail());
        cliente.setTelefono(datos.getTelefono());
        cliente.setFechaRegistro(datos.getFechaRegistro());
        return cliente;
    }

    private ClienteDTO aDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setIdCliente(cliente.getIdCliente());
        dto.setEstado(cliente.getEstado());
        dto.setNombre(cliente.getNombre());
        dto.setApellido(cliente.getApellido());
        dto.setEmail(cliente.getEmail());
        dto.setTelefono(cliente.getTelefono());
        dto.setFechaRegistro(cliente.getFechaRegistro());
        return dto;
    }
}
