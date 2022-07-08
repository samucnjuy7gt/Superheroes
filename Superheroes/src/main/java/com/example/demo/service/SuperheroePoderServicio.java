package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SuperheroePoder;
import com.example.demo.model.SuperheroePoderKey;

public interface SuperheroePoderServicio {

	List<SuperheroePoder> listarSuperheroePoder();
	
	SuperheroePoder buscarSuperheroePoder(SuperheroePoderKey id) throws ResourceNotFoundException;
	
	SuperheroePoder crearSuperheroePoder(SuperheroePoder superheroePoder);
	
	void eliminarSuperheroePoder(SuperheroePoderKey id) throws ResourceNotFoundException;
}
