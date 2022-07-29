package com.Proyectofinalwed.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Proyectofinalwed.entity.Centros;
import com.Proyectofinalwed.service.CentrosService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class CentrosController {
	
	@Autowired
	private CentrosService servicio;
	
	@GetMapping("/centros")
    public List<Centros> index(){
        return servicio.MostrarCentros();
    }
	
	@PostMapping("/centros/uploads")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo,
			@RequestParam("id") Long id){
		
		Map<String,Object>  response = new HashMap<>();
		//buscar el cliente por el id recibido
		Centros centros = servicio.mostrarCentrosId(id);
		
		//preguntamos si el archivo es distinto de vacio
		if( !archivo.isEmpty() ) {
			//guardamos el nombre del archivo en esta variable
			String nombreArchivo =  UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename().replace(" ", "");
			
			//guardamos la ruta completa uploads/nombredelaimagen lo guardamos en
			//una variable de tipo path que es de java.io
			
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
		
			try {
				//copiamos el archivo fisico a la ruta que definimos en Path
				Files.copy(archivo.getInputStream(), rutaArchivo );
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del centro");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
			String nombreFotoAnterior = centros.getImagen();
			//verificamos que el cliente tenga registrado una imagen
			if(nombreFotoAnterior != null && nombreFotoAnterior.length()>0) {
				//preparamos la ruta a la imagen guardada
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				//verificamos que el archivo existe y se pueda leer
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					//borramos la imagen
					archivoFotoAnterior.delete();
				}
			}
			
		
			//guardamos el nombre de la imagen
			centros.setImagen(nombreArchivo);
			//registramos en base de datos
			servicio.guardar(centros);
			
			response.put("Curso", centros);
			response.put("mensaje","Imagen subida correctamente :"+nombreArchivo);
		
		}
		
	
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/centros/{nombreImagen:.+}")
	public ResponseEntity<Resource> verImagen(@PathVariable String nombreImagen){
	
		Path rutaArchivo = Paths.get("uploads").resolve(nombreImagen).toAbsolutePath();
		
		Resource recurso = null;
		
		try {
			//codigo para acceder al archivo por url
			recurso = new UrlResource(rutaArchivo.toUri());
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("no se puede cargar a la imagen: "+nombreImagen);
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+recurso.getFilename()+"\"");
		
		
		
		return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK);
	}

}


