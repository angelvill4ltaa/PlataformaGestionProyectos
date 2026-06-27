package com.api.pro.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.pro.entity.EstadoTarea;
import com.api.pro.entity.Prioridad;
import com.api.pro.entity.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {

    List<Tarea> findByUsuarioIdUsuario(Integer idUsuario);
    
    long countByEstado(EstadoTarea estado);
    
    Page<Tarea> findByTituloContainingIgnoreCaseOrDescripcionContainingIgnoreCase(String titulo, String descripcion, Pageable pageable);

    Page<Tarea> findByProyectoIdProyecto(Integer idProyecto, Pageable pageable);

    Page<Tarea> findByEstado(EstadoTarea estado, Pageable pageable);

    Page<Tarea> findByPrioridad(Prioridad prioridad, Pageable pageable);

}