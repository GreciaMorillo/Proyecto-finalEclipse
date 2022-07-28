package com.Proyectofinalwed.service;

import java.util.List;

import com.Proyectofinalwed.entity.Centros;


public interface CentrosService {
	
	public List<Centros>MostrarCentros();
	
	public Centros mostrarCentrosId(long id);
	
	public Centros mostrarNombreCentros(String nombre);
	
	
	

}
