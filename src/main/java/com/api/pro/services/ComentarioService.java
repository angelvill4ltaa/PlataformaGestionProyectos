package com.api.pro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.api.pro.entity.Comentario;
import com.api.pro.repository.ComentarioRepository;

@Service
public class ComentarioService
        extends ICRUDImpl<Comentario,Integer>{

    @Autowired
    private ComentarioRepository repo;

    @Override
    JpaRepository<Comentario, Integer> getRepository() {
        return repo;
    }
    
    public List<Comentario> listarPorTarea(Integer idTarea){
        return repo.findByTareaIdTareaOrderByFechaComentarioDesc(idTarea);
    }
}
