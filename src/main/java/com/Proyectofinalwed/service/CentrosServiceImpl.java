package com.Proyectofinalwed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.Proyectofinalwed.entity.Centros;
import com.Proyectofinalwed.repository.CentrosDao;


public class CentrosServiceImpl implements CentrosService  {

	@Autowired
	private CentrosDao centroDao;

	@Override
	@Transactional(readOnly = true)
	public List<Centros> MostrarCentros() {
		return (List<Centros>)centroDao.findAll();
	}

	@Override
	public Centros mostrarCentrosId(long id) {
		return centroDao.findById(id).orElse(null);
	}

	@Override
	public Centros mostrarNombreCentros(String nombre) {
		return centroDao.findByNombre(nombre);
	}
}

