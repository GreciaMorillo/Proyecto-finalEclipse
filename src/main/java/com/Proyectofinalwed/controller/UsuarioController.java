package com.Proyectofinalwed.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.Proyectofinalwed.entity.Ciudades;
import com.Proyectofinalwed.entity.Usuario;
import com.Proyectofinalwed.service.CiudadesService;
import com.Proyectofinalwed.service.UsuarioService;


@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
    private UsuarioService servicio;
		
	
	@GetMapping("/usuario")
    public List<Usuario> index(){
        return servicio.mostrarTodos();
    }
	
	@GetMapping("/usuario/{id}")
	    public ResponseEntity<?> show(@PathVariable long id) {

	        Usuario usuario = null;
	        Map<String,Object>  response = new HashMap<>();

	        try {

	        	usuario = servicio.mostrarporId(id);

	        } catch (DataAccessException e) {
	            response.put("mensaje", "Error al realizar en base de datos");
	            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	        if(usuario == null) {
	            response.put("mensaje", "El Usuario con ID: "+id+" no existe en la base de datos");
	            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
	        }

	        return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
	    }
	    
    @PostMapping("/usuario")
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
    	Usuario clienteNew = null;
        Map<String,Object>  response = new HashMap<>();

        try {

            clienteNew =  servicio.guardar(usuario);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar en base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El Usuario ha sido creado con éxito");
        response.put("Usuario", clienteNew);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }    
	  
    @PutMapping("/usuario/{id}")
    public ResponseEntity<?> update(@RequestBody Usuario usuario
            ,@PathVariable Long id) {

    	Usuario usuarioUpdate =  servicio.mostrarporId(id);
        Map<String,Object>  response = new HashMap<>();
        if(usuarioUpdate == null) {
            response.put("mensaje","No existe el registro con id:"+id);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        try {


            usuarioUpdate.setNombre(usuario.getNombre());
            usuarioUpdate.setApellido(usuario.getApellido());
            usuarioUpdate.setEmail(usuario.getEmail());
            usuarioUpdate.setTelefono(usuario.getTelefono());
           

            //guardo y retorno los datos actualizados
            servicio.guardar(usuarioUpdate);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar en base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El cliente ha sido actualizado con éxito");
        response.put("Usuario", usuarioUpdate);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

    }
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
    	Usuario usuarioBorrado = servicio.mostrarporId(id);
        Map<String,Object>  response = new HashMap<>();

        if(usuarioBorrado == null) {
            response.put("mensaje","No existe el registro con id:"+id);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        
            servicio.borrar(id);

        response.put("mensaje","El Usuario ha sido eliminado con éxito");
        response.put("usuario", usuarioBorrado);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

    }
    
    @GetMapping("usuario/email/{email}")
    public Usuario buscarporEmail(@PathVariable String email) {
		return servicio.buscarporEmail(email);
      	
    }
    
    
    

}

