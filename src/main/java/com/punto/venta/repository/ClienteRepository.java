package com.punto.venta.repository;

import com.punto.venta.entity.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByEstadoTrueOrderByIdClienteDesc();

    List<Cliente> findByEstadoTrueAndNombreContainingIgnoreCaseOrderByIdClienteDesc(String nombre);

    List<Cliente> findTop3ByEstadoTrueAndNombreContainingIgnoreCaseOrderByIdClienteDesc(String nombre);
}
