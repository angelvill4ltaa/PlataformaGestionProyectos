package com.api.pro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Page;

import com.api.pro.entity.Proyecto;
import com.api.pro.services.ProyectoService;

@Controller
public class ProyectoViewController {

    private final ProyectoService service;

    public ProyectoViewController(ProyectoService service) {
        this.service = service;
    }

    @GetMapping("/proyectos")
    public String listarProyectos(@RequestParam(defaultValue = "0") int pagina,
            @RequestParam(required = false) String buscar, Model model) throws Exception {

        if(buscar != null && !buscar.isBlank()){
            model.addAttribute("proyectos", service.buscar(buscar));
            model.addAttribute("buscar",buscar);
            model.addAttribute("paginaActual",0);
            model.addAttribute("totalPaginas",1);

        }else{

            Page<Proyecto> proyectos = service.listarPaginado(pagina);
            model.addAttribute("proyectos", proyectos.getContent());
            model.addAttribute("paginaActual", pagina);
            model.addAttribute("totalPaginas", proyectos.getTotalPages());
        }

        return "proyectos";
    }

    @PostMapping("/proyectos/guardar")
    public String guardarProyecto(Proyecto proyecto, RedirectAttributes redirect) {

        try {
            service.registrar(proyecto);
            redirect.addFlashAttribute("success", "Proyecto registrado correctamente");
            
        } catch (Exception e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/proyectos";
    }

    @PostMapping("/proyectos/actualizar")
    public String actualizarProyecto(Proyecto proyecto, RedirectAttributes redirect) {

        try {

            service.actualizar(proyecto);
            redirect.addFlashAttribute("success", "Proyecto actualizado correctamente");

        } catch (Exception e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/proyectos";
    }

    @GetMapping("/proyectos/eliminar/{id}")
    public String eliminarProyecto(
            @PathVariable Integer id, RedirectAttributes redirect) {

        try {
            service.eliminar(id);
            redirect.addFlashAttribute("success", "Proyecto eliminado correctamente");

        } catch (Exception e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/proyectos";
    }
}