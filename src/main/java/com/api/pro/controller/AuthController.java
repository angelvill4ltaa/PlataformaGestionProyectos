package com.api.pro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.api.pro.entity.Usuario;
import com.api.pro.services.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

	private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String autenticar(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes redirect) {

        Usuario usuario = usuarioService.login(username, password);

        if (usuario == null) {
            redirect.addFlashAttribute("error", "Correo o contraseña incorrectos");
            return "redirect:/login";
        }

        session.setAttribute("usuario", usuario);
        String rol = usuario.getRol().getNombre();

        if (rol.equalsIgnoreCase("Administrador")) {
            return "redirect:/index";
        }

        if (rol.equalsIgnoreCase("Desarrollador")) {
            return "redirect:/desarrollador";
        }

        if (rol.equalsIgnoreCase("Gerente")) {
            return "redirect:/gerente";
        }

        return "redirect:/index";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
    @GetMapping("/perfil")
    public String perfil(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "perfil";
    }
    
}
