package com.api.pro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.pro.entity.Proyecto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
	
	List<Proyecto> findByNombreContainingIgnoreCase(String nombre);

	Page<Proyecto> findAll(Pageable pageable);

}