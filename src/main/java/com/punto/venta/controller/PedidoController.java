package com.punto.venta.controller;

import com.punto.venta.dto.PedidoDTO;
import com.punto.venta.entity.Pedido;
import com.punto.venta.service.PedidoService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService servicio;

    public PedidoController(PedidoService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<Pedido> listar() {
        return servicio.obtenerPedidos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido registrar(@RequestBody PedidoDTO datosPedido) {
        return servicio.registrarPedido(datosPedido);
    }
}
