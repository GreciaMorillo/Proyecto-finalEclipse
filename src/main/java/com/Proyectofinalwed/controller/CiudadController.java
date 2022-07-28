package com.Proyectofinalwed.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Proyectofinalwed.entity.Ciudades;
import com.Proyectofinalwed.service.CiudadesService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class CiudadController {
	
	@Autowired
	private CiudadesService serviciociudad;
	
	@GetMapping("/ciudades")
    public List<Ciudades> index(){
        return serviciociudad.mostrarCiudades();
	}
    @GetMapping("/ciudades/{nombre}")
    public ResponseEntity<?> show(@PathVariable String nombre) {

        Ciudades ciudades = null;
        Map<String,Object>  response = new HashMap<>();

        try {

        	ciudades = serviciociudad.mostrarNombre(nombre);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar en base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(ciudades == null) {
            response.put("mensaje", "La ciudad "+nombre+" no existe en la base de datos");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Ciudades>(ciudades,HttpStatus.OK);
    }
}
