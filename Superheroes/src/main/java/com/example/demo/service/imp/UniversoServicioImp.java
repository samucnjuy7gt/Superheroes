package com.example.demo.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SuperheroeUniverso;
import com.example.demo.model.Universo;
import com.example.demo.repository.UniversoRepositorio;
import com.example.demo.service.SuperheroeServicio;
import com.example.demo.service.UniversoServicio;

@Service
public class UniversoServicioImp implements UniversoServicio{
	
	@Autowired private UniversoRepositorio universoRepositorio;
	@Autowired private SuperheroeServicio ss;
	
	static final String MENSAJE = "No se ha encontrado el universo con el id ";

	@Override
	public List<Universo> listarUniversos() {
		return (List<Universo>) universoRepositorio.findAll();
	}

	@Override
	public Universo buscarUniverso(Integer id) throws ResourceNotFoundException {
		return universoRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
	}

	@Override
	public Universo crearUniverso(Universo universo) throws ResourceNotFoundException {
		boolean is = universoRepositorio.findById(universo.getId()).isPresent();
		
		Universo u;
		if(is) {
			u = actualizarUniverso(universo.getId(), universo);
		}
		else {
			u = universoRepositorio.save(universo);
		}
		
		return universoRepositorio.save(u);
	}

	@Override
	public Universo actualizarUniverso(Integer id, Universo universo) throws ResourceNotFoundException {
		Universo u = universoRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		u.setNombre(universo.getNombre());
		
		return universoRepositorio.save(u);
	}

	@Override
	public void eliminarUniverso(Integer id) throws ResourceNotFoundException {
		Universo u = universoRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		u.getSuperheroes().forEach(s -> {
			try {
				ss.eliminarSuperheroe(s.getSuperheroe().getId());
			} catch (ResourceNotFoundException e) {
				System.out.println(e.getMessage());
			}
		});
		
		List<SuperheroeUniverso> l = new ArrayList<>();
		
		u.setSuperheroes(l);
		
		universoRepositorio.delete(u);
	}

}
