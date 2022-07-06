package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SuperheroeUniverso;
import com.example.demo.model.SuperheroeUniversoKey;

public interface SuperheroeUniversoServicio {

	List<SuperheroeUniverso> listarSuperheroeUniverso();
	
	SuperheroeUniverso buscarSuperheroeUnverso(SuperheroeUniversoKey id) throws ResourceNotFoundException;
	
	SuperheroeUniverso crearSuperheroeUniverso(SuperheroeUniverso superheroeUniverso);
	
	void eliminarSuperheroeUniverso(SuperheroeUniversoKey id) throws ResourceNotFoundException;
}
