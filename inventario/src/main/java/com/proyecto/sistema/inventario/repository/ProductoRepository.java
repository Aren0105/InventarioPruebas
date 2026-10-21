package com.proyecto.sistema.inventario.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.sistema.inventario.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("""
            select p.nombre from Producto p
            where lower(p.nombre) like lower(concat('%', :q, '%'))
            order by p.nombre asc
                    """)
    List<String> sugerirNombres(@Param("q") String q, Pageable pagable);

    @Query("select p from Producto p order by rand()")
    List<Producto> listadoAleatorio(Pageable pagable);

    List<Producto> findByNombre(String nombre);

    List<Producto> findByStockLessThan(Integer stock);

    List<Producto> findByCategoriaId(Long categoriaId);

    List<Producto> findByNombreAndCategoriaId(String nombre, Long categoriaId);

    @Query("SELECT p FROM Producto p WHERE " +
            "(:nombre IS NULL OR :nombre = '' OR p.nombre = :nombre) AND " +
            "(:categoriaId IS NULL OR p.categoria.id = :categoriaId)")
    List<Producto> buscarYFiltrar(@Param("nombre") String nombre, @Param("categoriaId") Long categoriaId,
            Sort sort);

}
