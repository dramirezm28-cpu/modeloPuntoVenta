package com.punto.venta.repository;

import com.punto.venta.entity.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByEstadoTrueOrderByIdPedidoDesc();

    List<Pedido> findByEstadoTrueAndIdCliente_NombreContainingIgnoreCaseOrderByIdPedidoDesc(String nombreCliente);

    List<Pedido> findTop3ByEstadoTrueAndIdCliente_NombreContainingIgnoreCaseOrderByIdPedidoDesc(String nombreCliente);
}
