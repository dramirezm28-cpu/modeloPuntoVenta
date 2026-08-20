package com.punto.venta.service;

import com.punto.venta.dto.CategoriaDTO;
import com.punto.venta.entity.Categoria;
import com.punto.venta.repository.CategoriaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    private final CategoriaRepository repositorio;

    public CategoriaService(CategoriaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<CategoriaDTO> obtenerCategorias() {
        return repositorio.findAll().stream()
                .map(this::aDTO)
                .toList();
    }

    public CategoriaDTO registrarCategoria(CategoriaDTO datos) {
        Categoria categoria = nuevaCategoria(datos);
        return aDTO(repositorio.save(categoria));
    }

    public CategoriaDTO actualizarCategoria(Integer id, CategoriaDTO datos) {
        Categoria categoria = buscarPorId(id);
        categoria.setNombre(datos.getNombre());
        categoria.setDescripcion(datos.getDescripcion());
        return aDTO(repositorio.save(categoria));
    }

    public CategoriaDTO anularCategoria(Integer id) {
        Categoria categoria = buscarPorId(id);
        categoria.setEstado(false);
        return aDTO(repositorio.save(categoria));
    }

    public void eliminarCategoria(Integer id) {
        if (!repositorio.existsById(id)) {
            throw new RuntimeException("La categoria no existe con id " + id);
        }
        repositorio.deleteById(id);
    }

    private Categoria buscarPorId(Integer id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("La categoria no existe con id " + id));
    }

    private Categoria nuevaCategoria(CategoriaDTO datos) {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(datos.getIdCategoria());
        categoria.setNombre(datos.getNombre());
        categoria.setDescripcion(datos.getDescripcion());
        categoria.setEstado(true);
        return categoria;
    }

    private CategoriaDTO aDTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setIdCategoria(categoria.getIdCategoria());
        dto.setEstado(categoria.getEstado());
        dto.setNombre(categoria.getNombre());
        dto.setDescripcion(categoria.getDescripcion());
        return dto;
    }
}
