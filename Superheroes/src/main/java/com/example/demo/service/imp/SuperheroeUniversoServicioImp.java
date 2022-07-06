package com.example.demo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SuperheroeUniverso;
import com.example.demo.model.SuperheroeUniversoKey;
import com.example.demo.repository.SuperheroeUniversoRepositorio;
import com.example.demo.service.SuperheroeUniversoServicio;

@Service
public class SuperheroeUniversoServicioImp implements SuperheroeUniversoServicio{
	
	@Autowired
	private SuperheroeUniversoRepositorio sur;
	
	static final String MENSAJE = "No se ha encontrado el universo con el id ";

	@Override
	public List<SuperheroeUniverso> listarSuperheroeUniverso() {
		return (List<SuperheroeUniverso>) sur.findAll();
	}

	@Override
	public SuperheroeUniverso buscarSuperheroeUnverso(SuperheroeUniversoKey id) throws ResourceNotFoundException {
		return sur.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
	}

	@Override
	public SuperheroeUniverso crearSuperheroeUniverso(SuperheroeUniverso superheroeUniverso) {
		return sur.save(superheroeUniverso);
	}

	@Override
	public void eliminarSuperheroeUniverso(SuperheroeUniversoKey id) throws ResourceNotFoundException {
		SuperheroeUniverso su = sur.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		sur.delete(su);
	}

}
