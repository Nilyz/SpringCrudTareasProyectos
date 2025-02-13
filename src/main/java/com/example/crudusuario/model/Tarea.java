package com.example.crudusuario.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tareas")
public class Tarea {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String titulo; // Nombre correcto: titulo

	private String descripcion;

	@Column(name = "fecha_limite") // Nombre de columna correcto: fecha_limite
	@Temporal(TemporalType.DATE)
	private Date fechaLimite; // Nombre correcto: fechaLimite y tipo Date

	@Enumerated(EnumType.STRING) // Ahora es un ENUM
	private EstadoTarea estado; // Tipo correcto: EstadoTarea (ENUM)

	@ManyToOne // Relación con Proyecto
	@JoinColumn(name = "proyecto_id")
	private Proyecto proyecto;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() { // Getter y Setter para titulo
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

	public Date getFechaLimite() { // Getter y Setter para fechaLimite
		return fechaLimite;
	}

	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public EstadoTarea getEstado() { // Getter y Setter para estado (ENUM)
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