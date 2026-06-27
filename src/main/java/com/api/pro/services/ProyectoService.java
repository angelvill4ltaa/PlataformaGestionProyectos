package com.api.pro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.api.pro.entity.Proyecto;
import com.api.pro.repository.ProyectoRepository;

@Service
public class ProyectoService extends ICRUDImpl<Proyecto,Integer>{

    @Autowired
    private ProyectoRepository repo;

    @Override
    JpaRepository<Proyecto, Integer> getRepository() {
        return repo;
    }
    
    public List<Proyecto> buscar(String texto) {
        return repo.findByNombreContainingIgnoreCase(texto);
    }

    public Page<Proyecto> listarPaginado(int pagina){
        Pageable pageable = PageRequest.of(pagina,10);
        return repo.findAll(pageable);
    }
    
    public long totalProyectos() {
        return repo.count();
    }
}
