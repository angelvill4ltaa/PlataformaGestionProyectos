package com.api.pro.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.api.pro.entity.Proyecto;
import com.api.pro.services.ProyectoService;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    private final ProyectoService service;

    public ProyectoController(ProyectoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Proyecto> listar() throws Exception {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Proyecto buscar(@PathVariable Integer id) throws Exception {
        return service.buscarPorCodigo(id);
    }

    @PostMapping
    public Proyecto registrar(@RequestBody Proyecto proyecto) throws Exception {
        return service.registrar(proyecto);
    }

    @PutMapping
    public Proyecto actualizar(@RequestBody Proyecto proyecto) throws Exception {
        return service.actualizar(proyecto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) throws Exception {
        service.eliminar(id);
    }
}