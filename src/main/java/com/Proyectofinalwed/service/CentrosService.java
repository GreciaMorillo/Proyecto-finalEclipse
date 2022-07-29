package com.Proyectofinalwed.service;

import java.util.List;

import com.Proyectofinalwed.entity.Centros;
import com.Proyectofinalwed.entity.Cursos;


public interface CentrosService {
	
	public List<Centros>MostrarCentros();
	
	public Centros mostrarCentrosId(long id);
	
	public Centros guardar(Centros centros);
	
	public Centros mostrarNombreCentros(String nombre);
	
	
	

}
