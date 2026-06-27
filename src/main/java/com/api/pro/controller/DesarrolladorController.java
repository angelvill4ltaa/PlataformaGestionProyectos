package com.api.pro.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.api.pro.entity.EstadoTarea;
import com.api.pro.entity.Tarea;
import com.api.pro.entity.Usuario;
import com.api.pro.services.TareaService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DesarrolladorController {

    private final TareaService tareaService;

    public DesarrolladorController(TareaService tareaService) {
        this.tareaService = tareaService;
    }
    
    @GetMapping("/desarrollador")
    public String dashboard(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Tarea> tareas = tareaService.porUsuario(usuario.getIdUsuario());
        model.addAttribute("tareas", tareas);
        model.addAttribute("hora", java.time.LocalTime.now().getHour());

        return "desarrollador";
    }
    
    @PostMapping("/desarrollador/cambiar-estado")
    public String cambiarEstado(Integer idTarea, String estado) throws Exception {
        Tarea tarea = tareaService.buscarPorCodigo(idTarea);
        tarea.setEstado(EstadoTarea.valueOf(estado));
        tareaService.actualizar(tarea);

        return "redirect:/desarrollador";
    }
    
    @GetMapping("/tarea/iniciar/{id}")
    public String iniciar(@PathVariable Integer id) {
        tareaService.cambiarEstado(id, EstadoTarea.EN_PROCESO);

        return "redirect:/desarrollador";
    }
    
    @GetMapping("/tarea/finalizar/{id}")
    public String finalizar(@PathVariable Integer id) {
        tareaService.cambiarEstado(id, EstadoTarea.FINALIZADO);

        return "redirect:/desarrollador";
    }
    
}