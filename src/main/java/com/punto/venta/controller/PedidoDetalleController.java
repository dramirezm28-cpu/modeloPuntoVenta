package com.punto.venta.controller;

import com.punto.venta.dto.PedidoDetalleDTO;
import com.punto.venta.entity.PedidoDetalle;
import com.punto.venta.service.PedidoDetalleService;
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
@RequestMapping("/pedido-detalles")
public class PedidoDetalleController {

    private final PedidoDetalleService servicio;

    public PedidoDetalleController(PedidoDetalleService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<PedidoDetalle> listar() {
        return servicio.obtenerDetalles();
    }

    // pedido_detalle no tiene ESTADO propio: se toma como activo si el pedido esta activo.
    @GetMapping("/activos")
    public List<PedidoDetalleDTO> mostrarActivos() {
        return servicio.mostrarActivos();
    }

    // El filtro se aplica al nombre del producto del detalle.
    @GetMapping("/activos/filtro")
    public List<PedidoDetalleDTO> mostrarActivosFiltro(@RequestParam String filtro) {
        return servicio.mostrarActivosFiltro(filtro);
    }

    @GetMapping("/activos/filtro/top")
    public List<PedidoDetalleDTO> mostrarActivosFiltroTop(@RequestParam String filtro) {
        return servicio.mostrarActivosFiltroTop(filtro);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDetalle registrar(@RequestBody PedidoDetalleDTO datosDetalle) {
        return servicio.registrarDetalle(datosDetalle);
    }
}
