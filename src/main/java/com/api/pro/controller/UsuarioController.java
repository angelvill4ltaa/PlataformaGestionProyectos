package com.api.pro.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.api.pro.entity.Usuario;
import com.api.pro.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Usuario> listar() throws Exception {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable Integer id) throws Exception {
        return service.buscarPorCodigo(id);
    }

    @PostMapping
    public Usuario registrar(@RequestBody Usuario usuario) throws Exception {
        return service.registrar(usuario);
    }

    @PutMapping
    public Usuario actualizar(@RequestBody Usuario usuario) throws Exception {
        return service.actualizar(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) throws Exception {
        service.eliminar(id);
    }
}