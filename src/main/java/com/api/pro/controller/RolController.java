package com.api.pro.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.api.pro.entity.Rol;
import com.api.pro.services.RolService;

@RestController
@RequestMapping("/roles")
public class RolController {

    private final RolService service;

    public RolController(RolService service) {
        this.service = service;
    }

    @GetMapping
    public List<Rol> listar() throws Exception {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Rol buscar(@PathVariable Integer id) throws Exception {
        return service.buscarPorCodigo(id);
    }

    @PostMapping
    public Rol registrar(@RequestBody Rol rol) throws Exception {
        return service.registrar(rol);
    }

    @PutMapping
    public Rol actualizar(@RequestBody Rol rol) throws Exception {
        return service.actualizar(rol);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) throws Exception {
        service.eliminar(id);
    }
}