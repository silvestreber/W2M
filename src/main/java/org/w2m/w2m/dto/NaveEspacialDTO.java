package org.w2m.w2m.dto;

import java.io.Serializable;

public class NaveEspacialDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;
	private String aparicion;
	private String tipo;

	public NaveEspacialDTO() {
		super();
	}

	public NaveEspacialDTO(Long id, String nombre, String aparicion, String tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.aparicion = aparicion;
		this.tipo = tipo;
	}
	
	public NaveEspacialDTO(String nombre, String aparicion, String tipo) {
		super();
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
		return "NaveEspacialDTO [id=" + id + ", nombre=" + nombre + ", aparicion=" + aparicion + ", tipo=" + tipo + "]";
	}

}
