package com.Proyectofinalwed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Proyectofinalwed.entity.Centros;
import com.Proyectofinalwed.entity.Ciudades;
import com.Proyectofinalwed.repository.CiudadesDao;

@Service
public class CiudadesServiceImpl implements CiudadesService {
	
	@Autowired
	private CiudadesDao ciudadDao;

	@Override
	@Transactional(readOnly = true)
	public List<Ciudades> mostrarCiudades() {
		return (List<Ciudades>) ciudadDao.findAll();
	}

	@Override
	public Ciudades mostrarCiudadId(long id) {
		return ciudadDao.findById(id).orElse(null);
	}

	@Override
	public Ciudades mostrarNombre(String nombre) {
		return ciudadDao.findByNombre(nombre);

	}


	

}
