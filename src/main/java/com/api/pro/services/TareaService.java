package com.api.pro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.api.pro.entity.EstadoTarea;
import com.api.pro.entity.Prioridad;
import com.api.pro.entity.Tarea;
import com.api.pro.repository.TareaRepository;

@Service
public class TareaService extends ICRUDImpl<Tarea,Integer>{

    @Autowired
    private TareaRepository repo;

    @Override
    JpaRepository<Tarea, Integer> getRepository() {
        return repo;
    }
    
    public List<Tarea> porUsuario(Integer idUsuario){
        return repo.findByUsuarioIdUsuario(idUsuario);
    }
    
    public Page<Tarea> listarPaginado(int pagina){
        Pageable pageable = PageRequest.of(pagina,10);
        return repo.findAll(pageable);
    }
    
    public Page<Tarea> buscar(String texto, int pagina){
        Pageable pageable = PageRequest.of(pagina, 10);
        return repo.findByTituloContainingIgnoreCaseOrDescripcionContainingIgnoreCase(texto, texto, pageable);
    }
    
    public Page<Tarea> filtrarProyecto(Integer proyecto, int pagina){
        Pageable pageable = PageRequest.of(pagina, 10);
        return repo.findByProyectoIdProyecto(proyecto, pageable);
    }
    
    public Page<Tarea> filtrarEstado(EstadoTarea estado, int pagina){
        Pageable pageable = PageRequest.of(pagina,10);
        return repo.findByEstado(estado, pageable);
    }
    
    public Page<Tarea> filtrarPrioridad(Prioridad prioridad, int pagina){
        Pageable pageable = PageRequest.of(pagina,10);
        return repo.findByPrioridad(prioridad, pageable);
    }
    
    public long totalTareas() {
        return repo.count();
    }

    public long tareasPendientes() {
        return repo.countByEstado(EstadoTarea.PENDIENTE);
    }

    public long tareasProceso() {
        return repo.countByEstado(EstadoTarea.EN_PROCESO);
    }

    public long tareasFinalizadas() {
        return repo.countByEstado(EstadoTarea.FINALIZADO);
    }
    
    public void cambiarEstado(Integer idTarea, EstadoTarea estado) {
        Tarea tarea = repo.findById(idTarea).orElse(null);

        if (tarea != null) {
            tarea.setEstado(estado);
            repo.save(tarea);
        }
    }
}