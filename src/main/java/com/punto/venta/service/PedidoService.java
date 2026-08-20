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
}
