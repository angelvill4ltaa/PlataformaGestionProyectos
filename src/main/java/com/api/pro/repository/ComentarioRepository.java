package com.api.pro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.pro.entity.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer>{
	
	List<Comentario> findByTareaIdTareaOrderByFechaComentarioDesc(Integer idTarea);
}