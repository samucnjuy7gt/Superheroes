package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SuperheroeDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Poder;
import com.example.demo.model.Superheroe;
import com.example.demo.model.Universo;
import com.example.demo.service.SuperheroeServicio;

@RestController
@RequestMapping("/api/v1")
public class SuperheroeController {

	@Autowired
	private SuperheroeServicio servicio;
	
	@GetMapping("/superheroes")
	public List<SuperheroeDTO> listarSuperheroes(){
		final List<SuperheroeDTO> result = new ArrayList<>();
		
		final List<Superheroe> resultFromDB = servicio.listarSuperheroes();
		
		resultFromDB.forEach(superheroe -> result.add(convertToDTO(superheroe)));
		
		return result;
	}
	
	private SuperheroeDTO convertToDTO(Superheroe superheroe) {
		return new SuperheroeDTO(superheroe);
	}
	
	@GetMapping("/superheroes/{id}")
	public ResponseEntity<Superheroe> buscarSupereroe(@PathVariable(value = "id") Integer superheroeId)
		throws ResourceNotFoundException{
		Superheroe superheroe = servicio.buscarSuperheroe(superheroeId);
		
		return ResponseEntity.ok().body(superheroe);
		
	}
	
	@GetMapping("/superheroe/{param}")
	public ResponseEntity<List<Superheroe>> buscarSuperheroeNombre(@PathVariable(value = "param") String s){
		List<Superheroe> superheroes = servicio.buscarSuperheroeNombre(s);
		
		return ResponseEntity.ok().body(superheroes);
	}
	
	@PostMapping("/superheroe")
	public ResponseEntity<SuperheroeDTO> crearSuperheroe(@RequestBody SuperheroeDTO superH) throws ResourceNotFoundException {
		Superheroe superheroe = createSuperheroeFromDTO(superH);
		List<Poder> poderes = createPoderesFromDTO(superH);
		Superheroe s = servicio.crearSuperheroe(superheroe, poderes);
		return ResponseEntity.ok(convertToDTO(s));
	}
	
	private Superheroe createSuperheroeFromDTO(SuperheroeDTO superDTO) {
		Universo u = new Universo(superDTO.getUniversoId(), superDTO.getUniversoNombre());
		
		return new Superheroe(superDTO.getId(), superDTO.getNombre(), superDTO.isVivo(), u);
	}
	
	private List<Poder> createPoderesFromDTO(SuperheroeDTO superDTO){
		List<Poder> poderes = new ArrayList<>();
		Integer id;
		String nombre;
		
		for(int i = 0; i < superDTO.getPoderesId().size(); i++) {
			id = superDTO.getPoderesId().get(i);
			nombre = superDTO.getPoderesNombre().get(i);
			Poder p = new Poder(id, nombre);
			poderes.add(p);
		}
		
		return poderes;
	}
	
	@PutMapping("/superheroe/{id}")
	public ResponseEntity<SuperheroeDTO> actualizarSuperheroe(@RequestBody SuperheroeDTO superH, 
			@PathVariable(value = "id") Integer superheroeId) throws ResourceNotFoundException{
		Superheroe superheroe = createSuperheroeFromDTO(superH);
		Superheroe s = servicio.actualizarSuperheroe(superheroeId, superheroe);
		return ResponseEntity.ok(convertToDTO(s));
	}
	
	@DeleteMapping("/superheroe/{id}")
	public ResponseEntity<Superheroe> eliminarSuperheroe(@PathVariable(value = "id") Integer superheroeId) 
			throws ResourceNotFoundException{
		servicio.eliminarSuperheroe(superheroeId);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/superheroe/{id}")
	public ResponseEntity<SuperheroeDTO> matarSuperheroe(@PathVariable(value = "id") Integer superheroeId) throws ResourceNotFoundException{
		Superheroe s = servicio.matarSuperheroe(superheroeId);
		return ResponseEntity.ok(convertToDTO(s));
	}
	
	@PutMapping("/superheroe/{id}")
	public ResponseEntity<SuperheroeDTO> revivirSuperheroe(@PathVariable(value = "id") Integer superheroeId) throws ResourceNotFoundException{
		Superheroe s = servicio.revivirSuperheroe(superheroeId);
		return ResponseEntity.ok(convertToDTO(s));
	}
}
