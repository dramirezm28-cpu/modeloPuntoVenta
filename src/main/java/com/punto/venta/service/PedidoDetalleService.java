package com.punto.venta.service;

import com.punto.venta.dto.PedidoDetalleDTO;
import com.punto.venta.entity.Pedido;
import com.punto.venta.entity.PedidoDetalle;
import com.punto.venta.entity.Producto;
import com.punto.venta.repository.PedidoDetalleRepository;
import com.punto.venta.repository.PedidoRepository;
import com.punto.venta.repository.ProductoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PedidoDetalleService {

    private final PedidoDetalleRepository detalles;
    private final PedidoRepository pedidos;
    private final ProductoRepository productos;

    public PedidoDetalleService(PedidoDetalleRepository detalles,
                                PedidoRepository pedidos,
                                ProductoRepository productos) {
        this.detalles = detalles;
        this.pedidos = pedidos;
        this.productos = productos;
    }

    public List<PedidoDetalle> obtenerDetalles() {
        return detalles.findAll();
    }

    public PedidoDetalle registrarDetalle(PedidoDetalleDTO datos) {
        Pedido pedido = pedidos.findById(datos.getIdPedido())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        Producto producto = productos.findById(datos.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        PedidoDetalle detalle = crearDetalle(datos, pedido, producto);
        return detalles.save(detalle);
    }

    private PedidoDetalle crearDetalle(PedidoDetalleDTO datos, Pedido pedido, Producto producto) {
        PedidoDetalle detalle = new PedidoDetalle();
        detalle.setCantidad(datos.getCantidad());
        detalle.setPrecioUnitario(datos.getPrecioUnitario());
        detalle.setSubtotal(datos.getSubtotal());
        detalle.setIdPedido(pedido);
        detalle.setIdProducto(producto);
        return detalle;
    }
}
