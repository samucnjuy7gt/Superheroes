package com.example.demo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Poder;
import com.example.demo.repository.PoderRepositorio;
import com.example.demo.service.PoderServicio;

@Service
public class PoderServicioImp implements PoderServicio{
	
	@Autowired
	private PoderRepositorio poderRepositorio;
	
	static final String MENSAJE = "No se ha encontrado el universo con el id ";

	@Override
	public List<Poder> listarPoderes() {
		return (List<Poder>) poderRepositorio.findAll();
	}

	@Override
	public Poder buscarPoder(Integer id) throws ResourceNotFoundException {
		return poderRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
	}

	@Override
	public Poder crearPoder(Poder poder) throws ResourceNotFoundException {
		boolean is = poderRepositorio.findById(poder.getId()).isPresent();
		
		Poder p;
		if(is) {
			p = actualizarPoder(poder.getId(), poder);
		}
		else {
			p = poderRepositorio.save(poder);
		}
		
		return poderRepositorio.save(p);
	}

	@Override
	public Poder actualizarPoder(Integer id, Poder poder) throws ResourceNotFoundException {
		Poder p = poderRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		p.setNombre(poder.getNombre());
		
		return poderRepositorio.save(p);
	}

	@Override
	public void eliminarPoder(Integer id) throws ResourceNotFoundException {
		Poder p = poderRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		poderRepositorio.delete(p);
	}

}
