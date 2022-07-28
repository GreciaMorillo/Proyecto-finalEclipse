package com.Proyectofinalwed.service;

import java.util.List;

import com.Proyectofinalwed.entity.Centros;
import com.Proyectofinalwed.entity.Ciudades;


public interface CiudadesService {

	//metodo para mostar todos
	 public List<Ciudades> mostrarCiudades();
	 
	//metodo para mostar por Id
	 public Ciudades mostrarCiudadId(long id);
	 
	 public Ciudades mostrarNombre(String nombre);
	 
	 
	
	
}
