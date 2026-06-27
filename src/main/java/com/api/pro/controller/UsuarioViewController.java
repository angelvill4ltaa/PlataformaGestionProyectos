package com.api.pro.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.api.pro.entity.Usuario;
import com.api.pro.services.RolService;
import com.api.pro.services.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioViewController {

    private final UsuarioService usuarioService;
    private final RolService rolService;

    public UsuarioViewController(UsuarioService usuarioService, RolService rolService) {

        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(@RequestParam(defaultValue = "0") int pagina,
            @RequestParam(required = false) String buscar,
            @RequestParam(required = false) Integer rol,
            @RequestParam(required = false) String ordenar, Model model)

            throws Exception {

        model.addAttribute("roles", rolService.listar());
        model.addAttribute("ordenar", ordenar);
        model.addAttribute("rolSeleccionado", rol);

        if ("nombre".equals(ordenar)) {
        	
        	Page<Usuario> usuarios = usuarioService.listarOrdenadoPorNombre(pagina);

            model.addAttribute("usuarios", usuarios.getContent());
            model.addAttribute("paginaActual", pagina);
            model.addAttribute("totalPaginas", usuarios.getTotalPages());
        }

        else if ("fecha".equals(ordenar)) {
        	
        	Page<Usuario> usuarios = usuarioService.listarOrdenadoPorFecha(pagina);

            		model.addAttribute("usuarios", usuarios.getContent());
                    model.addAttribute("paginaActual", pagina);
                    model.addAttribute("totalPaginas", usuarios.getTotalPages());
        }

        else if (rol != null) {

            model.addAttribute("usuarios", usuarioService.filtrarPorRol(rol));
            model.addAttribute("paginaActual",0);
            model.addAttribute("totalPaginas",1);
        }

        else if (buscar != null && !buscar.isBlank()) {

            model.addAttribute("usuarios",usuarioService.buscar(buscar));
            model.addAttribute("paginaActual",0);
            model.addAttribute("totalPaginas",1);
            model.addAttribute("buscar",buscar);
        }

        else {

            Page<Usuario> usuarios = usuarioService.listarPaginado(pagina);

            model.addAttribute("usuarios",usuarios.getContent());
            model.addAttribute("paginaActual",pagina);
            model.addAttribute("totalPaginas",usuarios.getTotalPages());
        }

        return "usuarios";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(Usuario usuario, RedirectAttributes redirect) {

        try {

            usuarioService.registrar(usuario);

            redirect.addFlashAttribute("success", "Usuario registrado correctamente");

        } catch (Exception e) {

            redirect.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/usuarios";
    }

    @PostMapping("/usuarios/actualizar")
    public String actualizarUsuario(
            Usuario usuario,
            RedirectAttributes redirect) {

        try {

            usuarioService.actualizar(usuario);

            redirect.addFlashAttribute(
                    "success",
                    "Usuario actualizado correctamente");

        } catch (Exception e) {

            redirect.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/estado/{id}")
    public String cambiarEstado(@PathVariable Integer id, HttpSession session, RedirectAttributes redirect) {

        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");

        if (usuarioLogueado != null &&
            usuarioLogueado.getIdUsuario().equals(id)) {

            redirect.addFlashAttribute("error", "No puedes desactivar tu propia cuenta");

            return "redirect:/usuarios";
        }

        try {

            usuarioService.cambiarEstado(id);
            redirect.addFlashAttribute("success", "Estado actualizado correctamente");

        } catch (Exception e) {

            redirect.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/usuarios";
    }
}