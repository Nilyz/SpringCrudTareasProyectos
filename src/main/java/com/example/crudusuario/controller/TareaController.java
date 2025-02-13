package com.example.crudusuario.controller;

import com.example.crudusuario.model.Tarea;
import com.example.crudusuario.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping
    public String listarTareas(Model model) {
        List<Tarea> tareas = tareaService.obtenerTodasLasTareas();
        model.addAttribute("tareas", tareas);
        return "listar_tareas"; // Ahora busca en templates/tareas/listar_tareas.html
    }

    @GetMapping("/{id}")
    public String verTarea(@PathVariable Long id, Model model) {
        Tarea tarea = tareaService.obtenerTareaPorId(id).orElse(null);
        model.addAttribute("tarea", tarea);
        return "tareas/detalle"; // Ahora busca en templates/tareas/detalle.html
    }

    @GetMapping("/nueva")
    public String nuevaTareaForm(Model model) {
        model.addAttribute("tarea", new Tarea());
        return "tareas/agregar_tarea"; // Busca en templates/proyectos/agregar_tarea.html
    }

    @PostMapping("/guardar")
    public String guardarTarea(@ModelAttribute Tarea tarea) {
        tareaService.guardarTarea(tarea);
        return "redirect:/tareas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarTarea(@PathVariable Long id) {
        tareaService.eliminarTarea(id);
        return "redirect:/tareas";
    }
}
