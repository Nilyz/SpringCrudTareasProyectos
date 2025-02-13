package com.example.crudusuario.controller;

import com.example.crudusuario.model.Proyecto;
import com.example.crudusuario.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping("/nuevo")
    public String nuevoProyectoForm(Model model) {
        Proyecto proyecto = new Proyecto();
        proyecto.setFechaInicio(new Date()); // Initialize fechaInicio
        model.addAttribute("proyecto", proyecto);
        return "proyectos/agregar_proyectos"; // Correct template name: agregar_proyectos
    }

    @PostMapping("/guardar")
    public String guardarProyecto(@ModelAttribute Proyecto proyecto) {
        proyectoService.guardarProyecto(proyecto);
        return "redirect:/proyectos"; // Redirect to project list
    }

    @GetMapping
    public String listarProyectos(Model model) {
        List<Proyecto> proyectos = proyectoService.obtenerTodosLosProyectos();
        model.addAttribute("proyectos", proyectos);
        return "proyectos/lista_proyectos"; // Template for listing projects
    }
}