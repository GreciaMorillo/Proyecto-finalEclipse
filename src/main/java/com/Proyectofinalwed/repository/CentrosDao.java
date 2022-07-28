package com.Proyectofinalwed.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Proyectofinalwed.entity.Centros;


@Repository
public interface CentrosDao extends CrudRepository<Centros, Long> {
	
	
	public Centros findByNombre(String nombre);

}
