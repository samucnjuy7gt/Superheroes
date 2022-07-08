package com.example.demo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SuperheroePoder;
import com.example.demo.model.SuperheroePoderKey;
import com.example.demo.repository.SuperheroePoderRepositorio;
//import com.example.demo.repository.SuperheroePoderRepositorioHibernate;
import com.example.demo.service.SuperheroePoderServicio;

@Service
public class SuperheroePoderServicioImp implements SuperheroePoderServicio{
	
	//Implementacion Hibernate
	//@Autowired
	//private SuperheroePoderRepositorioHibernate sprh;

	//Implementacion CRUD
	@Autowired
	private SuperheroePoderRepositorio spr;
	
	static final String MENSAJE = "No se ha encontrado el universo con el id ";
	
	@Override
	public List<SuperheroePoder> listarSuperheroePoder() {
		//Implementcion Hibernate
		//return (List<SuperheroePoder>) sprh.findAll();
		
		//Implementacion CRUD
		return (List<SuperheroePoder>) spr.findAll();
	}

	@Override
	public SuperheroePoder buscarSuperheroePoder(SuperheroePoderKey id) throws ResourceNotFoundException {
		//Implementacion Hibernate
		//return sprh.findById(id)
				//.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		//Implementacion CRUD
		return spr.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
	}

	@Override
	public SuperheroePoder crearSuperheroePoder(SuperheroePoder superheroePoder) {
		//Implementacion Hibernate
		//return sprh.save(superheroePoder);
		
		//Implementacion CRUD
		return spr.save(superheroePoder);
	}

	@Override
	public void eliminarSuperheroePoder(SuperheroePoderKey id) throws ResourceNotFoundException {
		//Implementacion Hibernate
		//return sprh.findById(id)
				//.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		//Implementacion CRUD
		SuperheroePoder sp = spr.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		//Implementacion Hibernate
		//sprh.delete(sp);
		
		//Implementacion CRUD
		spr.delete(sp);
	}

}
