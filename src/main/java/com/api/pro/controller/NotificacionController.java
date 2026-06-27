package com.api.pro.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.api.pro.entity.Notificacion;
import com.api.pro.repository.NotificacionRepository;
import jakarta.servlet.http.HttpSession;
import com.api.pro.entity.Usuario;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    private final NotificacionRepository repo;

    public NotificacionController(NotificacionRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/mis")
    public List<Notificacion> misNotificaciones(HttpSession session) {
        Usuario u = (Usuario) session.getAttribute("usuario");
        if (u == null) return List.of();
        return repo.findByUsuarioIdUsuarioOrderByFechaDesc(u.getIdUsuario());
    }

    @PostMapping("/{id}/leer")
    public void marcarLeida(@PathVariable Integer id) {
        repo.findById(id).ifPresent(n -> { n.setLeido(true); repo.save(n); });
    }

    @PostMapping("/leer-todas")
    public void marcarTodas(HttpSession session) {
        Usuario u = (Usuario) session.getAttribute("usuario");
        if (u == null) return;
        repo.findByUsuarioIdUsuarioOrderByFechaDesc(u.getIdUsuario())
            .forEach(n -> { n.setLeido(true); repo.save(n); });
    }
}