package com.api.pro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.api.pro.services.ProyectoService;
import com.api.pro.services.TareaService;
import com.api.pro.services.UsuarioService;

@Controller
public class HomeController {

    private final UsuarioService usuarioService;
    private final ProyectoService proyectoService;
    private final TareaService tareaService;

    public HomeController(UsuarioService usuarioService, ProyectoService proyectoService, TareaService tareaService) {
        this.usuarioService = usuarioService;
        this.proyectoService = proyectoService;
        this.tareaService = tareaService;
    }	
	
	@GetMapping("/")
	public String landing() {
	    return "landing";
	}

	@GetMapping("/index")
	public String inicio(Model model) {
	    model.addAttribute("totalUsuarios", usuarioService.totalUsuarios());
	    model.addAttribute("usuariosActivos", usuarioService.usuariosActivos());
	    model.addAttribute("totalProyectos", proyectoService.totalProyectos());
	    model.addAttribute("totalTareas", tareaService.totalTareas());
	    model.addAttribute("tareasPendientes", tareaService.tareasPendientes());
	    model.addAttribute("tareasProceso", tareaService.tareasProceso());
	    model.addAttribute("tareasFinalizadas", tareaService.tareasFinalizadas());
	    return "index";
	}

}