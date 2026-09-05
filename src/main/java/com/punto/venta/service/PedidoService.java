package com.punto.venta.service;

import com.punto.venta.dto.PedidoDTO;
import com.punto.venta.entity.Cliente;
import com.punto.venta.entity.Pedido;
import com.punto.venta.repository.ClienteRepository;
import com.punto.venta.repository.PedidoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final PedidoRepository pedidos;
    private final ClienteRepository clientes;

    public PedidoService(PedidoRepository pedidos, ClienteRepository clientes) {
        this.pedidos = pedidos;
        this.clientes = clientes;
    }

    public List<Pedido> obtenerPedidos() {
        return pedidos.findAll();
    }

    public List<PedidoDTO> mostrarActivos() {
        return pedidos.findByEstadoTrueOrderByIdPedidoDesc().stream()
                .map(this::aDTO)
                .toList();
    }

    public List<PedidoDTO> mostrarActivosFiltro(String filtro) {
        return pedidos.findByEstadoTrueAndIdCliente_NombreContainingIgnoreCaseOrderByIdPedidoDesc(filtro).stream()
                .map(this::aDTO)
                .toList();
    }

    public List<PedidoDTO> mostrarActivosFiltroTop(String filtro) {
        return pedidos.findTop3ByEstadoTrueAndIdCliente_NombreContainingIgnoreCaseOrderByIdPedidoDesc(filtro).stream()
                .map(this::aDTO)
                .toList();
    }

    public Pedido registrarPedido(PedidoDTO datos) {
        Cliente cliente = obtenerCliente(datos.getIdCliente());
        Pedido pedido = crearPedido(datos, cliente);
        return pedidos.save(pedido);
    }

    private Cliente obtenerCliente(Integer idCliente) {
        return clientes.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    private Pedido crearPedido(PedidoDTO datos, Cliente cliente) {
        Pedido pedido = new Pedido();
        pedido.setEstado(datos.getEstado());
        pedido.setFechaPedido(datos.getFechaPedido());
        pedido.setEstadoPedido(datos.getEstadoPedido());
        pedido.setTotal(datos.getTotal());
        pedido.setIdCliente(cliente);
        return pedido;
    }

    private PedidoDTO aDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setIdPedido(pedido.getIdPedido());
        dto.setEstado(pedido.getEstado());
        dto.setFechaPedido(pedido.getFechaPedido());
        dto.setEstadoPedido(pedido.getEstadoPedido());
        dto.setTotal(pedido.getTotal());
        dto.setIdCliente(pedido.getIdCliente() != null ? pedido.getIdCliente().getIdCliente() : null);
        return dto;
    }
}
