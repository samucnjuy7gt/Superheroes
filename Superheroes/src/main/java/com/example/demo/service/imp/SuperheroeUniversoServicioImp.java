package com.example.demo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SuperheroeUniverso;
import com.example.demo.model.SuperheroeUniversoKey;
import com.example.demo.repository.SuperheroeUniversoRepositorio;
//import com.example.demo.repository.SuperheroeUniversoRepositorioHibernate;
import com.example.demo.service.SuperheroeUniversoServicio;

@Service
public class SuperheroeUniversoServicioImp implements SuperheroeUniversoServicio{
	
	//Implementacion Hibernate
	//@Autowired
	//private SuperheroeUniversoRepositorioHibernate surh;
	
	//Implementacion CRUD
	@Autowired
	private SuperheroeUniversoRepositorio sur;
	
	static final String MENSAJE = "No se ha encontrado el universo con el id ";

	@Override
	public List<SuperheroeUniverso> listarSuperheroeUniverso() {
		//Implementcion Hibernate
		//return (List<SuperheroeUniverso>) surh.findAll();
		
		//Implementacion CRUD
		return (List<SuperheroeUniverso>) sur.findAll();
	}

	@Override
	public SuperheroeUniverso buscarSuperheroeUniverso(SuperheroeUniversoKey id) throws ResourceNotFoundException {
		//Implementacion Hibernate
		//return surh.findById(id)
				//.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		//Implementacion CRUD
		return sur.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
	}

	@Override
	public SuperheroeUniverso crearSuperheroeUniverso(SuperheroeUniverso superheroeUniverso) {
		//Implementacion Hibernate
		//return surh.save(superheroeUniverso);
		
		//Implementacion CRUD
		return sur.save(superheroeUniverso);
	}

	@Override
	public void eliminarSuperheroeUniverso(SuperheroeUniversoKey id) throws ResourceNotFoundException {
		//Implementacion Hibernate
		//return surh.findById(id)
				//.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		//Implementacion CRUD
		SuperheroeUniverso su = sur.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		//Implementacion Hibernate
		//surh.delete(su);
		
		//Implementacion CRUD
		sur.delete(su);
	}

}
