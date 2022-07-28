package com.Proyectofinalwed.service;

import java.util.List;

import com.Proyectofinalwed.entity.Usuario;



public interface UsuarioService {
	
	//metodo para mostar todos los s
		 public List<Usuario> mostrarTodos();
		 
		//metodo para mostar un cliente por Id
		 public Usuario mostrarporId(long id);
		 
		//metodo para guardar un cliente
		 public Usuario guardar(Usuario usuario);
		 
		//metodo para Borrar un cliente
		 public void borrar(long id);
		 
		 public Usuario buscarporEmail(String email);
		 
		
		 

}
