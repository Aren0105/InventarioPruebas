package com.proyecto.sistema.inventario.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.proyecto.sistema.inventario.entities.Producto;
import com.proyecto.sistema.inventario.repository.ProductoRepository;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public List<Producto> listarProductosAleatorios() {
        return productoRepository.listadoAleatorio(PageRequest.of(0, 4));
    }

    public Producto guardarProducto(Producto p) {
        return productoRepository.save(p);
    }

    public Long contarProductos() {
        return productoRepository.count();
    }

    public Long bajoStock(Integer stock) {
        return productoRepository.findByStockLessThan(stock).stream().count();
    }

    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public List<String> sugerirNombre(String q) {
        String filtro = (q == null) ? "" : q.trim();
        return productoRepository.sugerirNombres(filtro, PageRequest.of(0, 10));
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    public List<Producto> listarProductosFiltrados(String q, Long categoriaId) {
        String nombre = (q == null) ? "" : q.trim();
        boolean tieneNombre = !nombre.isBlank();
        boolean tieneCategoria = (categoriaId != null);

        if (!tieneNombre && !tieneCategoria)
            return productoRepository.findAll();
        if (tieneNombre && !tieneCategoria)
            return productoRepository.findByNombre(nombre);
        if (!tieneNombre && tieneCategoria)
            return productoRepository.findByCategoriaId(categoriaId);

        return productoRepository.findByNombreAndCategoriaId(nombre, categoriaId);
    }

    // METODO PARA FILTRAR MEDIANTE NOMBRE, CATEGORIA Y ORDENAR POR STOCK O PRECIO
    // ESTE METODO ES ESPECIALMENTE PARA LA VISTA DEL CATALOGO
    public List<Producto> listarProductosOptimizado(String q, Long categoriaId, String orden) {
        Sort sort = Sort.unsorted();
        if ("asc".equalsIgnoreCase(orden))
            sort = Sort.by(Sort.Direction.ASC, "precio");
        else if ("desc".equalsIgnoreCase(orden))
            sort = Sort.by(Sort.Direction.DESC, "precio");
        else if ("stock".equalsIgnoreCase(orden))
            sort = Sort.by(Sort.Direction.DESC, "stock");

        String nombre = (q == null) ? "" : q.trim();

        return productoRepository.buscarYFiltrar(nombre, categoriaId, sort);
    }
}
