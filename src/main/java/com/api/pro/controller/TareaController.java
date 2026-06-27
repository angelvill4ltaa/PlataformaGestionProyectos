package com.api.pro.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.api.pro.entity.Tarea;
import com.api.pro.services.TareaService;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaService service;

    public TareaController(TareaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Tarea> listar() throws Exception {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Tarea buscar(@PathVariable Integer id) throws Exception {
        return service.buscarPorCodigo(id);
    }

    @PostMapping
    public Tarea registrar(@RequestBody Tarea tarea) throws Exception {
        return service.registrar(tarea);
    }

    @PutMapping
    public Tarea actualizar(@RequestBody Tarea tarea) throws Exception {
        return service.actualizar(tarea);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) throws Exception {
        service.eliminar(id);
    }
}