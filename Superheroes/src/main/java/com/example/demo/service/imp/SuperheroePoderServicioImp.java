package com.example.demo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SuperheroePoder;
import com.example.demo.model.SuperheroePoderKey;
import com.example.demo.repository.SuperheroePoderRepositorio;
import com.example.demo.service.SuperheroePoderServicio;

@Service
public class SuperheroePoderServicioImp implements SuperheroePoderServicio{

	@Autowired
	private SuperheroePoderRepositorio spr;
	
	static final String MENSAJE = "No se ha encontrado el universo con el id ";
	
	@Override
	public List<SuperheroePoder> listarSuperheroePoder() {
		return (List<SuperheroePoder>) spr.findAll();
	}

	@Override
	public SuperheroePoder buscarSuperheroePoder(SuperheroePoderKey id) throws ResourceNotFoundException {
		return spr.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
	}

	@Override
	public SuperheroePoder crearSuperheroePoder(SuperheroePoder superheroePoder) {
		return spr.save(superheroePoder);
	}

	@Override
	public void eliminarSuperheroePoder(SuperheroePoderKey id) throws ResourceNotFoundException {
		SuperheroePoder sp = spr.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		spr.delete(sp);
	}

}
