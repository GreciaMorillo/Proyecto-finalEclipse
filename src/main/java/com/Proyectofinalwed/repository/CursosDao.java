package com.Proyectofinalwed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Proyectofinalwed.entity.Centros;
import com.Proyectofinalwed.entity.Cursos;

@Repository
public interface CursosDao extends CrudRepository<Cursos,Long>  {
	
	@Query("from Centros")
	public List<Centros> mostrarcursos();

}
