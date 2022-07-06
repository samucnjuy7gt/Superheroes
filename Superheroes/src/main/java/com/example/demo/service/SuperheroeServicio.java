package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Poder;
import com.example.demo.model.Superheroe;

public interface SuperheroeServicio {

	List<Superheroe> listarSuperheroes();
	
	Superheroe buscarSuperheroe(Integer id) throws ResourceNotFoundException;
	
	List<Superheroe> buscarSuperheroeNombre(String nombre);
	
	Superheroe crearSuperheroe(Superheroe superheroe, List<Poder> poderes) throws ResourceNotFoundException;
	
	Superheroe actualizarSuperheroe(Integer id, Superheroe superheroe) throws ResourceNotFoundException;
	
	void eliminarSuperheroe(Integer id) throws ResourceNotFoundException;
	
	Superheroe matarSuperheroe(Integer id) throws ResourceNotFoundException;
	
	Superheroe revivirSuperheroe(Integer id) throws ResourceNotFoundException;
}
