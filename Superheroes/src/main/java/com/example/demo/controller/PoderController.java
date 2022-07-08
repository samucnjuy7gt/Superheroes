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

import com.example.demo.dto.PoderDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Poder;
import com.example.demo.service.PoderServicio;

@RestController
@RequestMapping("/api/v1")
public class PoderController {

	@Autowired
	private PoderServicio servicio;
	
	@GetMapping("/poder")
	public List<PoderDTO> listarPoderes(){
		final List<PoderDTO> result = new ArrayList<>();
		
		final List<Poder> resultFromDB = servicio.listarPoderes();
		
		resultFromDB.forEach(poderDTO -> result.add(convertToDTO(poderDTO)));
		
		return result;
	}
	
	private PoderDTO convertToDTO(Poder poder) {
		return new PoderDTO(poder);
	}
	
	@GetMapping("/poder/{id}")
	public ResponseEntity<Poder> buscarPoder(@PathVariable(value = "id") Integer poderId)
		throws ResourceNotFoundException{
		Poder poder = servicio.buscarPoder(poderId);
		
		return ResponseEntity.ok().body(poder);
	}
	
	@PostMapping("/poder")
	public ResponseEntity<PoderDTO> crearPoder(@RequestBody PoderDTO pod) throws ResourceNotFoundException {
		Poder poder = createPoderFromDTO(pod);
		Poder p = servicio.crearPoder(poder);
		return ResponseEntity.ok(convertToDTO(p));
	}
	
	private Poder createPoderFromDTO(PoderDTO podDTO) {
		return new Poder(podDTO.getId(), podDTO.getNombre());
	}
	
	@PutMapping("/poder/{id}")
	public ResponseEntity<PoderDTO> actualizarPoder(@RequestBody PoderDTO pod, 
			@PathVariable(value = "id") Integer poderId) throws ResourceNotFoundException{
		Poder poder = createPoderFromDTO(pod);
		Poder p = servicio.actualizarPoder(poderId, poder);
		return ResponseEntity.ok(convertToDTO(p));
	}
	
	@DeleteMapping("/poder/{id}")
	public ResponseEntity<Poder> eliminarPoder(@PathVariable(value = "id") Integer poderId) 
			throws ResourceNotFoundException{
		servicio.eliminarPoder(poderId);
		
		return ResponseEntity.noContent().build();
	}
}
