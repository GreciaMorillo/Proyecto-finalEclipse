package com.Proyectofinalwed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Proyectofinalwed.entity.Usuario;
import com.Proyectofinalwed.repository.UsuarioDao;



	
	@Service
	public class UsuarioServiceImpl implements UsuarioService{ 
		
		  @Autowired
		    private UsuarioDao usuarioDao;

		    @Override
		    @Transactional(readOnly = true)
		    public List<Usuario> mostrarTodos() {
		        return (List<Usuario>) usuarioDao.findAll();
		    }

			@Override
			@Transactional(readOnly = true)
			public Usuario mostrarporId(long id) {
				return usuarioDao.findById(id).orElse(null);
			}

			@Override
			@Transactional
			public Usuario guardar(Usuario cliente) {
				// TODO Auto-generated method stub
				return usuarioDao.save(cliente);
			}

			@Override
			@Transactional
			public void borrar(long id) {
				// TODO Auto-generated method stub
				usuarioDao.deleteById(id);
			}

			@Override
			public Usuario buscarporEmail(String email) {
				// TODO Auto-generated method stub
				return usuarioDao.findByEmail(email);
			}

			

			


}
