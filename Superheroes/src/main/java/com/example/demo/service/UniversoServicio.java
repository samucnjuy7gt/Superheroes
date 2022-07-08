package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Universo;

public interface UniversoServicio {

	List<Universo> listarUniversos();
	
	Universo buscarUniverso(Integer id) throws ResourceNotFoundException;
	
	Universo crearUniverso(Universo universo)throws ResourceNotFoundException;
	
	Universo actualizarUniverso(Integer id, Universo universo) throws ResourceNotFoundException;
	
	void eliminarUniverso(Integer id) throws ResourceNotFoundException;
}
