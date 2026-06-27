package com.api.pro.controller;

import com.api.pro.entity.Tarea;
import com.api.pro.services.ProyectoService;
import com.api.pro.services.UsuarioService;
import com.api.pro.services.TareaService;
import com.api.pro.entity.Prioridad;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TareaViewController {
	
	private final ProyectoService proyectoService;
	private final UsuarioService usuarioService;
	private final TareaService service;

    public TareaViewController(TareaService service, UsuarioService usuarioService, ProyectoService proyectoService) {
        this.service = service;
		this.usuarioService = usuarioService;
		this.proyectoService = proyectoService;
    }
    
    @GetMapping("/tareas")
    public String tareas(@RequestParam(defaultValue = "0") int pagina,
                         @RequestParam(required = false) String buscar,
                         @RequestParam(required = false) Integer proyecto,
                         @RequestParam(required = false) String estado,
                         @RequestParam(required = false) String prioridad,
                         Model model) throws Exception {

        Page<Tarea> page;
        
        if (buscar != null && !buscar.isBlank()) {
            page = service.buscar(buscar, pagina);
        } else if (proyecto != null) {
            page = service.filtrarProyecto(proyecto, pagina);
        } else if (estado != null && !estado.isBlank()) {
            page = service.filtrarEstado(com.api.pro.entity.EstadoTarea.valueOf(estado), pagina);
        } else if (prioridad != null && !prioridad.isBlank()) {
            page = service.filtrarPrioridad(Prioridad.valueOf(prioridad), pagina);
        } else {
            page = service.listarPaginado(pagina);
        }
        model.addAttribute("tareas", page.getContent());
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("totalPaginas", page.getTotalPages());
        model.addAttribute("buscar", buscar);
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("proyectos", proyectoService.listar());
        return "tareas";
    }
    
    @PostMapping("/tareas/guardar")
    public String guardar(Tarea tarea) throws Exception {
        service.registrar(tarea);

        return "redirect:/tareas";
    }
    
    @PostMapping("/tareas/actualizar")
    public String actualizar(Tarea tarea, RedirectAttributes redirectAttributes) throws Exception {
        service.actualizar(tarea);

        redirectAttributes.addFlashAttribute("success", "Tarea actualizada correctamente"
        );
        return "redirect:/tareas";
    }
    
    @GetMapping("/tareas/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) throws Exception {
        service.eliminar(id);

        return "redirect:/tareas";
    }
    
    @GetMapping("/api/tareas/usuario/{id}")
    @ResponseBody
    public List<Tarea> tareasPorUsuario(@PathVariable Integer id) throws Exception {
        return service.listar().stream()
            .filter(t -> t.getUsuario().getIdUsuario().equals(id))
            .collect(java.util.stream.Collectors.toList());
    }
}