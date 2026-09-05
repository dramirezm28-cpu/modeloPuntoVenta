package com.punto.venta.repository;

import com.punto.venta.entity.PedidoDetalle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Integer> {

    // pedido_detalle no tiene columna ESTADO; se considera activo cuando su pedido esta activo.
    List<PedidoDetalle> findByIdPedido_EstadoTrueOrderByIdPedidoDetalleDesc();

    List<PedidoDetalle> findByIdPedido_EstadoTrueAndIdProducto_NombreContainingIgnoreCaseOrderByIdPedidoDetalleDesc(String nombreProducto);

    List<PedidoDetalle> findTop3ByIdPedido_EstadoTrueAndIdProducto_NombreContainingIgnoreCaseOrderByIdPedidoDetalleDesc(String nombreProducto);
}
