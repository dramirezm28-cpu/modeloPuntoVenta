package com.punto.venta.service;

import com.punto.venta.dto.ProductoDTO;
import com.punto.venta.entity.Categoria;
import com.punto.venta.entity.Producto;
import com.punto.venta.repository.CategoriaRepository;
import com.punto.venta.repository.ProductoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    private final ProductoRepository productos;
    private final CategoriaRepository categorias;

    public ProductoService(ProductoRepository productos, CategoriaRepository categorias) {
        this.productos = productos;
        this.categorias = categorias;
    }

    public List<Producto> obtenerProductos() {
        return productos.findAll();
    }

    public List<ProductoDTO> mostrarActivos() {
        return productos.findByEstadoTrueOrderByIdProductoDesc().stream()
                .map(this::aDTO)
                .toList();
    }

    public List<ProductoDTO> mostrarActivosFiltro(String filtro) {
        return productos.findByEstadoTrueAndNombreContainingIgnoreCaseOrderByIdProductoDesc(filtro).stream()
                .map(this::aDTO)
                .toList();
    }

    public List<ProductoDTO> mostrarActivosFiltroTop(String filtro) {
        return productos.findTop3ByEstadoTrueAndNombreContainingIgnoreCaseOrderByIdProductoDesc(filtro).stream()
                .map(this::aDTO)
                .toList();
    }

    public Producto registrarProducto(ProductoDTO datos) {
        Categoria categoria = buscarCategoria(datos.getIdCategoria());
        Producto producto = crearProducto(datos, categoria);
        return productos.save(producto);
    }

    private Categoria buscarCategoria(Integer idCategoria) {
        return categorias.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
    }

    private Producto crearProducto(ProductoDTO datos, Categoria categoria) {
        Producto producto = new Producto();
        producto.setEstado(datos.getEstado());
        producto.setNombre(datos.getNombre());
        producto.setDescripcion(datos.getDescripcion());
        producto.setPrecio(datos.getPrecio());
        producto.setStock(datos.getStock());
        producto.setIdCategoria(categoria);
        return producto;
    }

    private ProductoDTO aDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(producto.getIdProducto());
        dto.setEstado(producto.getEstado());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setIdCategoria(producto.getIdCategoria() != null ? producto.getIdCategoria().getIdCategoria() : null);
        return dto;
    }
}
