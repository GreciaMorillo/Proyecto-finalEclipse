package com.Proyectofinalwed.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Proyectofinalwed.entity.Centros;
import com.Proyectofinalwed.entity.Ciudades;


@Repository
public interface CiudadesDao extends CrudRepository<Ciudades, Long>  {
	
	
	public Ciudades findByNombre(String nombre);
	
	@Query("from Centros")
	public Centros findByNombre1(String nombre);

}