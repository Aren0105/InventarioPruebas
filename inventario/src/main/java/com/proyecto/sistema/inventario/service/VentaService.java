package com.proyecto.sistema.inventario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.sistema.inventario.entities.Producto;
import com.proyecto.sistema.inventario.entities.Venta;
import com.proyecto.sistema.inventario.entities.VentaDetalle;
import com.proyecto.sistema.inventario.repository.ProductoRepository;
import com.proyecto.sistema.inventario.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Service
public class VentaService {
     private final ProductoRepository productoRepository;
    private final VentaRepository ventaRepository;

    VentaService(ProductoRepository productoRepository, VentaRepository ventaRepository) {
        this.productoRepository = productoRepository;
        this.ventaRepository = ventaRepository;
    }

    @Transactional
    public Venta agregarDetalle(Venta v, String nombreProducto, Integer cantidad, Double descuentoPct) {

        Producto producto = productoRepository.findByNombre(nombreProducto).stream().findFirst().orElseThrow();

        double precio = producto.getPrecio();
        double subtotalLinea = precio * cantidad;
        subtotalLinea = Math.round(subtotalLinea * 100.0) / 100.0;
        double descuentoLinea = (descuentoPct == null) ? 0 : subtotalLinea * descuentoPct / 100;
        descuentoLinea = Math.round(descuentoLinea * 100.0) / 100.0;
        double subtotalConDescuento = subtotalLinea - descuentoLinea;

        VentaDetalle detalle = new VentaDetalle();
        detalle.setVenta(v);
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);
        detalle.setPrecioUnitario(precio);
        detalle.setDescuento(descuentoLinea);
        detalle.setSubtotal(subtotalConDescuento);

        v.getDetalles().add(detalle);

        return v;

    }

    public void recalcularTotales(Venta v) {
        double subtotal = v.getDetalles().stream().mapToDouble(VentaDetalle::getSubtotal).sum();
        double descuento = v.getDetalles().stream().mapToDouble(d -> d.getDescuento() == null ? 0 : d.getDescuento())
                .sum();
        double igv = (subtotal - descuento) * 0.18;
        double igvFormateado = Math.round(igv * 100.0) / 100.0;
        double total = subtotal - descuento + igv;
        total = Math.round(total * 100.0) / 100.0;

        v.setSubTotal(subtotal);
        v.setDescuento(descuento);
        v.setIgv(igvFormateado);
        v.setTotal(total);
    }

    public void eliminarDetalle(Venta v, Long productoId) {
        boolean eliminado = v.getDetalles().removeIf(d -> d.getProducto() != null &&
                d.getProducto().getId() != null &&
                d.getProducto().getId().equals(productoId));
        if (eliminado) {
            recalcularTotales(v);
        }
    }

    public double promedioVenta(){
        List<Venta> ventas = ventaRepository.findAll();
        double sumaTotal = 0;
        double promedioventa= 0;
        for (Venta v  : ventas) {
           sumaTotal += v.getTotal();
           promedioventa = sumaTotal / ventas.size();
           promedioventa = Math.round(promedioventa * 100.0) / 100.0;
        }
        return promedioventa;
    }

    @Transactional
    public void actualizarStock(Venta v) {
        for (VentaDetalle vd : v.getDetalles()) {
            Long productoId = vd.getProducto().getId();
            Producto p = productoRepository.findById(productoId).orElseThrow();

            int cantidad = vd.getCantidad();
            int nuevoStock = p.getStock() - cantidad;

            p.setStock(nuevoStock);
        }
    }

    @Transactional
    public Venta completarVenta(Venta v) {
        actualizarStock(v);
        return ventaRepository.save(v);
    }
}
