package com.punto.venta.controller;

import com.punto.venta.dto.CategoriaDTO;
import com.punto.venta.service.CategoriaService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService servicio;

    public CategoriaController(CategoriaService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<CategoriaDTO> listar() {
        return servicio.obtenerCategorias();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaDTO registrar(@RequestBody CategoriaDTO datos) {
        return servicio.registrarCategoria(datos);
    }

    @PutMapping("/{id}")
    public CategoriaDTO actualizar(@PathVariable Integer id, @RequestBody CategoriaDTO datos) {
        return servicio.actualizarCategoria(id, datos);
    }

    @PutMapping("/anular/{id}")
    public CategoriaDTO anular(@PathVariable Integer id) {
        return servicio.anularCategoria(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        servicio.eliminarCategoria(id);
    }
}
