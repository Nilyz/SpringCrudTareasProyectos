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
        proyecto.setFechaInicio(new Date());
        model.addAttribute("proyecto", proyecto);
        return "proyectos/agregar_proyectos";
    }

    @PostMapping("/guardar")
    public String guardarProyecto(@ModelAttribute Proyecto proyecto) {
        proyectoService.guardarProyecto(proyecto);
        return "redirect:/proyectos";
    }

    @GetMapping
    public String listarProyectos(Model model) {
        List<Proyecto> proyectos = proyectoService.obtenerTodosLosProyectos();
        model.addAttribute("proyectos", proyectos);
        return "proyectos/lista_proyectos";
    }

    @GetMapping("/{id}")
    public String verProyecto(@PathVariable Long id, Model model) {
        Proyecto proyecto = proyectoService.obtenerProyectoPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de proyecto inválido:" + id));

        List<Tarea> tareas = proyecto.getTareas();

        model.addAttribute("proyecto", proyecto);
        model.addAttribute("tareas", tareas);
        return "proyectos/ver_proyecto";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProyecto(@PathVariable Long id) {
        proyectoService.eliminarProyecto(id);
        return "redirect:/proyectos";
    }

    @GetMapping("/editar/{id}")
    public String editarProyecto(@PathVariable Long id, Model model) {
        Proyecto proyecto = proyectoService.obtenerProyectoPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de proyecto inválido:" + id));
        model.addAttribute("proyecto", proyecto);
        return "proyectos/editar_proyecto";
    }

    @PostMapping("/actualizar")
    public String actualizarProyecto(@ModelAttribute Proyecto proyecto) {
        proyectoService.guardarProyecto(proyecto);
        return "redirect:/proyectos";
    }
}