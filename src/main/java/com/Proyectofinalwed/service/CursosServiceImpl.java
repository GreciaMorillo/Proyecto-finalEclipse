package com.Proyectofinalwed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.Proyectofinalwed.entity.Cursos;
import com.Proyectofinalwed.repository.CursosDao;

@Service
public class CursosServiceImpl implements CursosService {
	
	@Autowired
	private CursosDao cursosDao;

	@Override
	@Transactional(readOnly = true)
	public List<Cursos> mostrarTodos() {
		return (List<Cursos>) cursosDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cursos mostrarporId(long id) {
		// TODO Auto-generated method stub
		return cursosDao.findById(id).orElse(null);
	}

	@Override
	public Cursos guardar(Cursos cursos) {
		return cursosDao.save(cursos);
	}

	@Override
	public void borrar(long id) {
		cursosDao.deleteById(id);
		
	}
	
	
}
