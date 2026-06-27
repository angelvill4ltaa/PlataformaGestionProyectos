package com.api.pro.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.api.pro.entity.Usuario;
import com.api.pro.repository.UsuarioRepository;

@Service
public class UsuarioService extends ICRUDImpl<Usuario,Integer>{

    @Autowired
    private UsuarioRepository repo;
    
    @Autowired
    private TareaService tareaService;

    @Override
    JpaRepository<Usuario, Integer> getRepository() {
        return repo;
    }
    
    public List<Usuario> buscar(String texto) {
        return repo.findByNombreContainingIgnoreCaseOrCorreoContainingIgnoreCase(texto,texto);
    }
    
    @Override
    public Usuario registrar(Usuario usuario) {
    	
    	if(repo.findByCorreo(usuario.getCorreo()).isPresent()) { 
    		throw new RuntimeException("El correo ya esta registrado"); 
    	}    
        usuario.setFechaRegistro(LocalDate.now());
        usuario.setActivo(true);
        return repo.save(usuario);
    }
    
    @Override
    public Usuario actualizar(Usuario usuario) throws Exception {

        Usuario actual = repo.findById(usuario.getIdUsuario()).orElseThrow();
        
        Optional<Usuario> correoExistente = repo.findByCorreo(usuario.getCorreo());
        
        if(correoExistente.isPresent()
                && !correoExistente.get().getIdUsuario().equals(usuario.getIdUsuario())) {
        
           throw new RuntimeException("El correo ya existe");
        }
        actual.setNombre(usuario.getNombre());
        actual.setCorreo(usuario.getCorreo());
        actual.setRol(usuario.getRol());

        return repo.save(actual);
    }
    
    public Page<Usuario> listarPaginado(int pagina) {
        Pageable pageable = PageRequest.of(pagina, 10);
        return repo.findAll(pageable);
    }
    
    public List<Usuario> filtrarPorRol(Integer idRol) {
        return repo.findByRol_IdRol(idRol);
    }
    
    public List<Usuario> ordenarPorNombre() {
        return repo.findAllByOrderByNombreAsc();
    }

    public List<Usuario> ordenarPorFecha() {
        return repo.findAllByOrderByFechaRegistroDesc();
    }
    
    public Page<Usuario> listarOrdenadoPorNombre(int pagina) {
        Pageable pageable = PageRequest.of(pagina, 10);
        return repo.findAllByOrderByNombreAsc(pageable);
    }

    public Page<Usuario> listarOrdenadoPorFecha(int pagina) {
        Pageable pageable = PageRequest.of(pagina, 10);
        return repo.findAllByOrderByFechaRegistroDesc(pageable);
    }
    
    public Usuario login(String correo, String password) {

        Usuario usuario = repo.findByCorreoAndPassword(correo, password).orElse(null);

        if (usuario == null) {
            return null;
        }
        if (!usuario.getActivo()) {
            return null;
        }
        return usuario;
    }
    
    public void cambiarEstado(Integer id) {
        Usuario usuario = repo.findById(id).orElseThrow();
        usuario.setActivo(!usuario.getActivo());
        repo.save(usuario);
    }
    
    public long totalUsuarios() {
        return repo.count();
    }

    public long usuariosActivos() {
        return repo.countByActivoTrue();
    }
}