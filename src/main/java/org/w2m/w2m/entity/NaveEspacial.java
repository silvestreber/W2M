package org.w2m.w2m.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NaveEspacial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String aparicion;
	private String tipo;

	public NaveEspacial() {
		super();
	}

	public NaveEspacial(Long id, String nombre, String aparicion, String tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.aparicion = aparicion;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAparicion() {
		return aparicion;
	}

	public void setAparicion(String aparicion) {
		this.aparicion = aparicion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "NaveEspacial [id=" + id + ", nombre=" + nombre + ", aparicion=" + aparicion + ", tipo=" + tipo + "]";
	}

}
