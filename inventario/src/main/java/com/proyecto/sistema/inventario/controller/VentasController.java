package com.proyecto.sistema.inventario.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.proyecto.sistema.inventario.entities.Producto;
import com.proyecto.sistema.inventario.entities.Venta;
import com.proyecto.sistema.inventario.repository.ProductoRepository;
import com.proyecto.sistema.inventario.service.ProductoService;
import com.proyecto.sistema.inventario.service.VentaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ventas")
@SessionAttributes("ventaActual")
public class VentasController {
    private final ProductoRepository productoRepository;

    private final VentaService ventaService;

    private final ProductoService productoService;

    @ModelAttribute("ventaActual")
    public Venta ventaActual() {
        Venta v = new Venta();
        return v;
    }

    @GetMapping
    public String vistaVentas(@ModelAttribute("ventaActual") Venta venta, Model model) {
        ventaService.recalcularTotales(venta);
        model.addAttribute("venta", venta);
        model.addAttribute("promedioVenta", ventaService.promedioVenta());
        return "ventas";
    }

    @PostMapping("/agregar")
    public String agregarDetalle(
            @RequestParam String nombreProducto,
            @RequestParam Integer cantidad,
            @RequestParam(required = false) Double descuento,
            @ModelAttribute("ventaActual") Venta v) {
        ventaService.agregarDetalle(v, nombreProducto, cantidad, descuento);
        return "redirect:/ventas";
    }

    @PostMapping("/eliminar")
    public String eliminarDetalle(
            @RequestParam Long productoId,
            @ModelAttribute("ventaActual") Venta v) {
        ventaService.eliminarDetalle(v, productoId);
        return "redirect:/ventas";
    }

    @PostMapping("/completar")
    public String completarVenta(
            @RequestParam String metodoPago,
            @RequestParam String tipoRecibo,
            @RequestParam(required = false, defaultValue = "0.0") double pagoRecibido,
            @ModelAttribute("ventaActual") Venta v, SessionStatus status) {

        if (pagoRecibido == 0.0) {
            pagoRecibido = v.getTotal();
        }
        double vuelto = pagoRecibido - v.getTotal();
        vuelto = Math.round(vuelto * 100.0) / 100.0;
        v.setVuelto(vuelto);
        v.setMetodoPago(metodoPago);
        v.setTipoRecibo(tipoRecibo);
        v.setPagoRecibido(pagoRecibido);
        ventaService.completarVenta(v);
        status.setComplete();
        return "redirect:/ventas";
    }

    @GetMapping("/productos/sugerencia")
    @ResponseBody
    public List<String> sugerencia(@RequestParam String q) {
        return productoService.sugerirNombre(q);
    }

    // MOSTRAR PRECIO DEL PRODUCTO DURANTE LA VENTA
    @GetMapping("/venta/precio")
    @ResponseBody
    public double mostrarPrecio(@RequestParam String nombreProducto) {
        Producto producto = productoRepository.findByNombre(nombreProducto).stream().findFirst().orElseThrow();
        return producto != null ? producto.getPrecio() : 0.0;
    }

}
