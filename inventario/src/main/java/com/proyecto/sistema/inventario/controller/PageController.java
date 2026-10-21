package com.proyecto.sistema.inventario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.sistema.inventario.service.ProductoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/page")
public class PageController {
    private final ProductoService productoService;

    @GetMapping
    public String vistaPage(Model model) {
        model.addAttribute("fourProducts", productoService.listarProductosAleatorios());
        return "home";
    }
}
