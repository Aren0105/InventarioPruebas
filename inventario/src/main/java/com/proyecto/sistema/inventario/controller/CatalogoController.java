package com.proyecto.sistema.inventario.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proyecto.sistema.inventario.service.CategoriaService;
import com.proyecto.sistema.inventario.service.ProductoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/catalogo")
public class CatalogoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    @GetMapping
    public String vistaCatalogo(Model model, @RequestParam(required = false) String ordenP,
            @RequestParam(required = false) String q, @RequestParam(required = false) Long categoriaId) {
        model.addAttribute("filtroMejorado", productoService.listarProductosOptimizado(q, categoriaId, ordenP));
        model.addAttribute("categorias", categoriaService.listarCategorias());
        model.addAttribute("categoriaId", categoriaId);
        return "Catalogo";
    }

    @GetMapping("/catalogo/sugerencia")
    @ResponseBody
    public List<String> sugerencia(@RequestParam String q) {
        return productoService.sugerirNombre(q);
    }
}
