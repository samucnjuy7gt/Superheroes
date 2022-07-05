package com.example.demo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Superheroe;
import com.example.demo.repository.SuperheroeRepositorio;
import com.example.demo.service.SuperheroeServicio;

@Service
public class SuperheroeServicioImp implements SuperheroeServicio{
	
	@Autowired
	private SuperheroeRepositorio superheroeRepositorio;
	
	static final String MENSAJE = "No se ha encontrado el superheroe con el id ";

	@Override
	public List<Superheroe> listarSuperheroes() {
		return (List<Superheroe>) superheroeRepositorio.findAll();
	}

	@Override
	public Superheroe buscarSuperheroe(Integer id) throws ResourceNotFoundException{
		return superheroeRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
	}

	@Override
	public List<Superheroe> buscarSuperheroeNombre(String nombre) {
		return null;
	}

	@Override
	public Superheroe crearSuperheroe(Superheroe superheroe) {
		return superheroeRepositorio.save(superheroe);
	}
	
	@Override
	public Superheroe actualizarSuperheroe(Integer id,Superheroe superheroe) throws ResourceNotFoundException{
		Superheroe superH = superheroeRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
		
		superH.setNombre(superheroe.getNombre());
		superH.setVivo(superheroe.isVivo());
		superH.setUniverso(superheroe.getUniverso());
		superH.setPoder(superheroe.getPoder());
		
		return superheroeRepositorio.save(superH);
		
	}

	@Override
	public void eliminarSuperheroe(Integer id) throws ResourceNotFoundException{
		Superheroe superH = superheroeRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
		
		superheroeRepositorio.delete(superH);
	}

	@Override
	public void matarSuperheroe(Integer id) throws ResourceNotFoundException{
		Superheroe superheroe = superheroeRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
		
		superheroe.setVivo(false);
	}

	@Override
	public void revivirSuperheroe(Integer id) throws ResourceNotFoundException{
		Superheroe superheroe = superheroeRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
		
		superheroe.setVivo(true);
	}
}
