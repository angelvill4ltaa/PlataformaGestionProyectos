package com.api.pro.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tarea")
public class Tarea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tarea")
	private Integer idTarea;

	@Column(nullable = false, length = 100)
	private String titulo;

	@Column(nullable = false, length = 300)
	private String descripcion;

	@Column(name = "fecha_limite", nullable = false)
	private LocalDate fechaLimite;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EstadoTarea estado;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Prioridad prioridad;

	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_proyecto", nullable = false)
	private Proyecto proyecto;

}
