package com.gestion.empleados.controlador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.empleados.modelo.Empleado;
import com.gestion.empleados.repositorio.EmpleadoRepositorio;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/empleadoscontroller/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadoControlador {

	@Autowired
	private EmpleadoRepositorio repositorioEmpleados;
	
	@Operation(summary = "ESTE METODO LO QUE HACE ES DEVOLVER UNA LISTA DE TODOS LOS EMPLEADOS EN LA BASE DE DATOS")
	//ESTE METODO ES PARA CONSULTAR TODO LOS EMPLEADOS
	@GetMapping("/listarempleados")
	public List<Empleado> listarEmpleados(){
		return this.repositorioEmpleados.findAll();
	}
	
	@Operation(summary = "ESTE METODO GUARDA UN NUEVO EMPLEADO RECIBIENDO UN REQUESTBODY")
	//ESTE METODO ES PARA GUARDAR UN NUEVO EMPLEADO
	@PostMapping("/guardarempleado")
	public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
		return this.repositorioEmpleados.save(empleado);
	}
	
	@Operation(summary = "ESTE METODO ELIMINA A UN EMPLEADO BASANDOSE DESDE AL ID, DICHO METODO ELIMINA LA FOTO DEL EMPLEADO EN CASO DE SI TENGA GUARDADA UNA")
	//ESTE METODO ES PARA ELIMINAR UN EMPLEADO
	@DeleteMapping("/eliminarempleado/{id}")
	public ResponseEntity<?> eliminarEmpleado(@PathVariable("id") Long id) throws IOException {
		//AQUI SE ELIMINA LA IMAGEN RESPECTIVA DEL EMPLEADO EN CASO DE QUE TENGA UNA
		Path nombreImagen = Paths.get("C:\\Users\\hecto\\OneDrive\\Documentos\\myworkspace\\Angular\\empleados-front-end\\src\\assets\\imagenes\\"+id+".jpg");
		boolean result = Files.deleteIfExists(nombreImagen);
        
		this.repositorioEmpleados.deleteById(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@Operation(summary = "ESTE METODO RECIBE UN REQUESTBODY PARA PODER ACTUALIZAR UN EMPLEADO")
	//ESTE METODO ES PARA ACTUALIZAR LOS EMPLEADOS
	@PutMapping("/actualizarempleado")
	public Empleado actualizarEmpleado(@RequestBody Empleado empleado) {
		return this.repositorioEmpleados.save(empleado);
	}
	
	@Operation(summary = "ESTE METODO RECIBE 2 PARAMETROS, EL PRIMERO ES UN MULTIPARTFILE, EL CUAL ES LA IMAGEN DEL EMPLEADO, PARA EL SEGUNDO PARAMETRO LLEGA UN STRING CON FORMATO JSON CONVERTIDO DESDE ANGULAR, PARA QUE UNA VEZ QUE ES RECIBIDO EN EL CONTROLADOR JAVA SE CONVIERTA A UN OBJETO DE LA CLASE EMPLEADO")
	//ESTE METODO ES PARA GUARDAR LA IMAGEN DEL EMPLEADO
	@PostMapping(value="/guardarImagen")
	public ResponseEntity<?> guardarImagen(@RequestParam("image") MultipartFile imagen, @RequestParam("empleado")  String empleado) throws IOException {
		Empleado employee = new ObjectMapper().readValue(empleado, Empleado.class);
				
		//AQUI SE UBICA EN EL DIRECTORIO DETRO DE LA CARPETA DEL PROYECTO ANGULAR /assets ESTO CON LA INTENCION DE GUARDAR AHI LAS IMAGENES DE LOS EMPLEADOS PARA TENER UN FACIL ACCESO
		Path nombreImagen = Paths.get("C:\\Users\\hecto\\OneDrive\\Documentos\\myworkspace\\Angular\\empleados-front-end\\src\\assets\\imagenes\\"+employee.getId()+".jpg");

		//LA SIGUIENTE LINEA ES PARA EL CASO DE ACTUALIZACION DE FOTO, LA ELIMINARA EN CASO DE QUE EXISTA PARA PONER LA NUEVA EN LA CARPETA MENCIONADA
        boolean result = Files.deleteIfExists(nombreImagen);
		
		byte[] bytesImagen = imagen.getBytes();
		Files.write(nombreImagen, bytesImagen);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}














