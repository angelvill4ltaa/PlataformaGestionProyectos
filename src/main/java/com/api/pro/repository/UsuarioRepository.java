package com.api.pro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.pro.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByCorreo(String correo);

    Optional<Usuario> findByCorreoAndPassword(String correo, String password);
    
    List<Usuario> findByNombreContainingIgnoreCaseOrCorreoContainingIgnoreCase(String nombre, String correo);
    
    List<Usuario> findByRol_IdRol(Integer idRol);
    
    List<Usuario> findAllByOrderByNombreAsc();

    List<Usuario> findAllByOrderByFechaRegistroDesc();
    
    Page<Usuario> findAllByOrderByNombreAsc(Pageable pageable);

    Page<Usuario> findAllByOrderByFechaRegistroDesc(Pageable pageable);
    
	long countByActivoTrue();
}