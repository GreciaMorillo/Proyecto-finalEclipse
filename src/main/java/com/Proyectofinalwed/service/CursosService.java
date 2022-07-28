package com.Proyectofinalwed.service;

import java.util.List;

import com.Proyectofinalwed.entity.Cursos;



public interface CursosService {
	
	public List<Cursos> mostrarTodos();
	
	 public Cursos mostrarporId(long id);
	 
	 public Cursos guardar(Cursos cursos);
		 
	 public void borrar(long id);
		 

			
	

}
