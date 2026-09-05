package com.punto.venta.controller;

import com.punto.venta.dto.ProductoDTO;
import com.punto.venta.entity.Producto;
import com.punto.venta.service.ProductoService;
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
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService servicio;

    public ProductoController(ProductoService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<Producto> listar() {
        return servicio.obtenerProductos();
    }

    @GetMapping("/activos")
    public List<ProductoDTO> mostrarActivos() {
        return servicio.mostrarActivos();
    }

    @GetMapping("/activos/filtro")
    public List<ProductoDTO> mostrarActivosFiltro(@RequestParam String filtro) {
        return servicio.mostrarActivosFiltro(filtro);
    }

    @GetMapping("/activos/filtro/top")
    public List<ProductoDTO> mostrarActivosFiltroTop(@RequestParam String filtro) {
        return servicio.mostrarActivosFiltroTop(filtro);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Producto registrar(@RequestBody ProductoDTO datosProducto) {
        return servicio.registrarProducto(datosProducto);
    }
}
