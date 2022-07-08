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

import com.example.demo.dto.UniversoDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Universo;
import com.example.demo.service.UniversoServicio;

@RestController
@RequestMapping("/api/v1")
public class UniversoController {

	@Autowired
	private UniversoServicio servicio;
	
	@GetMapping("/universo")
	public List<UniversoDTO> listarUniversos(){
		final List<UniversoDTO> result = new ArrayList<>();
		
		final List<Universo> resultFromDB = servicio.listarUniversos();
		
		resultFromDB.forEach(universo -> result.add(convertToDTO(universo)));
		
		return result;
	}
	
	private UniversoDTO convertToDTO(Universo universo) {
		return new UniversoDTO(universo);
	}
	
	@GetMapping("/universo/{id}")
	public ResponseEntity<Universo> buscarUniverso(@PathVariable(value = "id") Integer universoId)
		throws ResourceNotFoundException{
		Universo universo = servicio.buscarUniverso(universoId);
		
		return ResponseEntity.ok().body(universo);
	}
	
	@PostMapping("/universo")
	public ResponseEntity<UniversoDTO> crearUniverso(@RequestBody UniversoDTO uni) throws ResourceNotFoundException {
		Universo universo = createUniversoFromDTO(uni);
		Universo u = servicio.crearUniverso(universo);
		return ResponseEntity.ok(convertToDTO(u));
	}
	
	private Universo createUniversoFromDTO(UniversoDTO uniDTO) {
		return new Universo(uniDTO.getId(), uniDTO.getNombre());
	}
	
	@PutMapping("/universo/{id}")
	public ResponseEntity<UniversoDTO> actualizarUniverso(@RequestBody UniversoDTO uni, 
			@PathVariable(value = "id") Integer universoId) throws ResourceNotFoundException{
		Universo universo = createUniversoFromDTO(uni);
		Universo u = servicio.actualizarUniverso(universoId, universo);
		return ResponseEntity.ok(convertToDTO(u));
	}
	
	@DeleteMapping("/universo/{id}")
	public ResponseEntity<Universo> eliminarUniverso(@PathVariable(value = "id") Integer universoId) 
			throws ResourceNotFoundException{
		servicio.eliminarUniverso(universoId);
		
		return ResponseEntity.noContent().build();
	}
}
