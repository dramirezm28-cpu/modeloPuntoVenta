package com.punto.venta.repository;

import com.punto.venta.entity.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByEstadoTrueOrderByIdProductoDesc();

    List<Producto> findByEstadoTrueAndNombreContainingIgnoreCaseOrderByIdProductoDesc(String nombre);

    List<Producto> findTop3ByEstadoTrueAndNombreContainingIgnoreCaseOrderByIdProductoDesc(String nombre);
}
