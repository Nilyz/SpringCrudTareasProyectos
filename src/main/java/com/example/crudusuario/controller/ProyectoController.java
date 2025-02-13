package com.example.crudusuario.controller;

import com.example.crudusuario.model.Proyecto;
import com.example.crudusuario.model.Tarea;
import com.example.crudusuario.service.ProyectoService;
import com.example.crudusuario.service.TareaService;
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

    @Autowired
    private TareaService tareaService;

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

    @GetMapping("/{id}")
    public String verProyecto(@PathVariable Long id, Model model) {
        Proyecto proyecto = proyectoService.obtenerProyectoPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de proyecto inválido:" + id));

        // Get tareas directly from the Proyecto object using the relationship
        List<Tarea> tareas = proyecto.getTareas();

        model.addAttribute("proyecto", proyecto);
        model.addAttribute("tareas", tareas);
        return "proyectos/ver_proyecto";
    }

    @GetMapping("/eliminar/{id}") // Changed to GetMapping for simplicity, consider DeleteMapping for RESTful API
    public String eliminarProyecto(@PathVariable Long id) {
        proyectoService.eliminarProyecto(id);
        return "redirect:/proyectos"; // Redirige a la lista de proyectos tras eliminar
    }
}