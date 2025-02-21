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
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping("/nueva")
    public String nuevaTareaForm(Model model) {
        Tarea tarea = new Tarea();
        tarea.setFechaLimite(new Date());
        List<Proyecto> proyectos = proyectoService.obtenerTodosLosProyectos();

        model.addAttribute("tarea", tarea);
        model.addAttribute("proyectos", proyectos);
        return "tareas/agregar_tarea";
    }

    @PostMapping("/guardar")
    public String guardarTarea(@ModelAttribute Tarea tarea, @RequestParam("proyectoId") Long proyectoId) {
        Proyecto proyecto = proyectoService.obtenerProyectoPorId(proyectoId).orElse(null);
        if (proyecto != null) {
            tarea.setProyecto(proyecto);
            tareaService.guardarTarea(tarea);
        }
        return "redirect:/proyectos";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarTarea(@PathVariable Long id) {
        tareaService.eliminarTarea(id);
        return "redirect:/tareas";
    }

    @GetMapping("/editar/{id}")
    public String editarTareaForm(@PathVariable Long id, Model model) {
        Tarea tarea = tareaService.obtenerTareaPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de tarea inválido:" + id));

        List<Proyecto> proyectos = proyectoService.obtenerTodosLosProyectos();

        model.addAttribute("tarea", tarea);
        model.addAttribute("proyectos", proyectos);
        return "tareas/editar_tarea";
    }

    @PostMapping("/actualizar")
    public String actualizarTarea(@ModelAttribute Tarea tarea, @RequestParam("proyectoId") Long proyectoId) {
        Proyecto proyecto = proyectoService.obtenerProyectoPorId(proyectoId).orElse(null);
        if (proyecto != null) {
            tarea.setProyecto(proyecto);
            tareaService.guardarTarea(tarea);
        }
        return "redirect:/proyectos";
    }
}