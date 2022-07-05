package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Poder;

public interface PoderServicio {

	List<Poder> listarPoderes();
	
	Poder buscarPoder(Integer id) throws ResourceNotFoundException;
	
	Poder crearPoder(Poder poder);
	
	Poder actualizarPoder(Integer id, Poder poder) throws ResourceNotFoundException;
	
	void eliminarUniverso(Integer id) throws ResourceNotFoundException;
}
