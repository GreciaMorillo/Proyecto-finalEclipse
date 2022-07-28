package com.Proyectofinalwed.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Proyectofinalwed.entity.Usuario;


@Repository
public interface UsuarioDao extends CrudRepository<Usuario, Long>  {
	
	
	public Usuario findByEmail(String email);

}
