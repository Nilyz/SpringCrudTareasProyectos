package com.example.crudusuario.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Entity
@Table(name = "tareas") // Maps to the 'tareas' table
public class Tarea {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrementing primary key
	private Long id;

	@Column(nullable = false) //  'titulo' is required (not nullable)
	private String titulo;

	private String descripcion; // 'descripcion' is optional (nullable by default)

	@Column(name = "fecha_limite") // Maps to 'fecha_limite' column in the table
	@Temporal(TemporalType.DATE) // Stores only the date part
	@DateTimeFormat(pattern = "yyyy-MM-dd") // For formatting date input/output
	private Date fechaLimite;

	@Enumerated(EnumType.STRING) // Stores enum values as Strings in the database
	private EstadoTarea estado; // 'estado' is an enum of type EstadoTarea

	@ManyToOne // Many Tareas belong to one Proyecto
	@JoinColumn(name = "proyecto_id") // Foreign key column in 'tareas' table is 'proyecto_id'
	private Proyecto proyecto; //  References the Proyecto entity

	public Tarea() {
		// Default constructor
	}

	// Getters and Setters for all fields (id, titulo, descripcion, fechaLimite, estado, proyecto)
	// ... (You already have these in your code, ensure they are present for all fields)

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public EstadoTarea getEstado() {
		return estado;
	}

	public void setEstado(EstadoTarea estado) {
		this.estado = estado;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
}