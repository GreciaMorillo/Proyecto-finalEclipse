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
	@Table(name="ciudad")
	public class Ciudades implements Serializable {	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String nombre;
	

	
	
		

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

