package com.proyecto.sistema.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.sistema.inventario.entities.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    
}
