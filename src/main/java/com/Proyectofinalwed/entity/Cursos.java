package com.Proyectofinalwed.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="cursos")
public class Cursos implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable= false)
	private String nombre;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Ciudad_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Ciudades ciudades;
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Centros_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Centros centros;
	
	private String imagen;
	
	
	
	
	public String getImagen() {
		return imagen;
	}



	public void setImagen(String imagen) {
		this.imagen = imagen;
	}



	public Ciudades getCiudades() {
		return ciudades;
	}



	public Centros getCentros() {
		return centros;
	}



	public void setCiudades(Ciudades ciudades) {
		this.ciudades = ciudades;
	}



	public void setCentros(Centros centros) {
		this.centros = centros;
	}



	public long getId() {
		return id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setId(long id) {
		this.id = id;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

}
