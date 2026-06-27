package com.api.pro.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.api.pro.entity.Comentario;
import com.api.pro.entity.Tarea;
import com.api.pro.entity.Usuario;
import com.api.pro.services.ComentarioService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    private final ComentarioService service;

    public ComentarioController(ComentarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Comentario> listar() throws Exception {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Comentario buscar(@PathVariable Integer id) throws Exception {
        return service.buscarPorCodigo(id);
    }

    @PostMapping
    public Comentario registrar(@RequestParam String mensaje,
                                 @RequestParam Integer idTarea,
                                 HttpSession session) throws Exception {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Tarea tarea = new Tarea();
        tarea.setIdTarea(idTarea);
        Comentario c = new Comentario();
        c.setMensaje(mensaje);
        c.setFechaComentario(java.time.LocalDateTime.now());
        c.setUsuario(usuario);
        c.setTarea(tarea);
        return service.registrar(c);
    }

    @PutMapping
    public Comentario actualizar(@RequestBody Comentario comentario) throws Exception {
        return service.actualizar(comentario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) throws Exception {
        service.eliminar(id);
    }
    
    @GetMapping("/tarea/{id}")
    public List<Comentario> comentariosPorTarea(@PathVariable Integer id){
        return service.listarPorTarea(id);
    }
    
    @PostMapping("/nueva")
    public Comentario nueva(@RequestParam String mensaje, @RequestParam Integer idTarea, HttpSession session) throws Exception {

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Tarea tarea = new Tarea();
        tarea.setIdTarea(idTarea);

        Comentario c = new Comentario();
        c.setMensaje(mensaje);
        c.setFechaComentario(LocalDateTime.now());
        c.setUsuario(usuario);
        c.setTarea(tarea);

        return service.registrar(c);
    }
}