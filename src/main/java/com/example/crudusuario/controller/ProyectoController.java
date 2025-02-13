package com.example.crudusuario.controller;

import com.example.crudusuario.model.Proyecto;
import com.example.crudusuario.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping
    public String listarProyectos(Model model) {
        List<Proyecto> proyectos = proyectoService.obtenerTodosLosProyectos();
        model.addAttribute("proyectos", proyectos);
        return "proyectos"; // Retorna la vista Thymeleaf "proyectos.html"
    }

    @GetMapping("/{id}")
    public String verProyecto(@PathVariable Long id, Model model) {
        Proyecto proyecto = proyectoService.obtenerProyectoPorId(id).orElse(null);
        model.addAttribute("proyecto", proyecto);
        return "detalle_proyecto"; // Vista detalle_proyecto.html
    }

    @GetMapping("/nuevo")
    public String nuevoProyectoForm(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        return "form_proyecto"; // Vista formulario proyecto.html
    }

    @PostMapping("/guardar")
    public String guardarProyecto(@ModelAttribute Proyecto proyecto) {
        proyectoService.guardarProyecto(proyecto);
        return "redirect:/proyectos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProyecto(@PathVariable Long id) {
        proyectoService.eliminarProyecto(id);
        return "redirect:/proyectos";
    }
}