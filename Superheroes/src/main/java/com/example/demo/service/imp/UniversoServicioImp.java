package com.example.demo.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SuperheroeUniverso;
import com.example.demo.model.Universo;
import com.example.demo.repository.UniversoRepositorio;
//import com.example.demo.repository.UniversoRepositorioHibernate;
import com.example.demo.service.SuperheroeServicio;
import com.example.demo.service.UniversoServicio;

@Service
public class UniversoServicioImp implements UniversoServicio{
	
	//Implementacion Hibernate
	//@Autowired private UniversoRepositorioHibernate universoRepositorioHibernate;
	
	//Implementacion CRUD
	@Autowired private UniversoRepositorio universoRepositorio;
	@Autowired private SuperheroeServicio ss;
	
	static final String MENSAJE = "No se ha encontrado el universo con el id ";

	@Override
	public List<Universo> listarUniversos() {
		//Implementacion Hibernate
		//return (List<Universo>) universoRepositorioHibernate.findAll();
		
		//Implementacion CRUD
		return (List<Universo>) universoRepositorio.findAll();
	}

	@Override
	public Universo buscarUniverso(Integer id) throws ResourceNotFoundException {
		//Implementacion Hibernate
		//return universoRepositorioHibernate.findById(id)
				//.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		//Implementacion CRUD
		return universoRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
	}

	@Override
	public Universo crearUniverso(Universo universo) throws ResourceNotFoundException {
		//Implementacion Hibernate
		//boolean is = universoRepositorioHibernate.findById(universo.getId()).isPresent();
		
		//Implementacion CRUD
		boolean is = universoRepositorio.findById(universo.getId()).isPresent();
		
		Universo u;
		if(is) {
			u = actualizarUniverso(universo.getId(), universo);
		}
		else {
			//Implementacion Hibernate
			//u = universoRepositorio.save(universo);
			
			//Implementacion CRUD
			u = universoRepositorio.save(universo);
		}
		
		return u;
	}

	@Override
	public Universo actualizarUniverso(Integer id, Universo universo) throws ResourceNotFoundException {
		//Implementacion Hibernate
		//return universoRepositorioHibernate.findById(id)
				//.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
				
		//Implementacion CRUD
		Universo u = universoRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		u.setNombre(universo.getNombre());
		
		//Implementacion Hibernate
		//return universoRepositorioHibernate.save(u);
		
		//Implementacion CRUD
		return universoRepositorio.save(u);
	}

	@Override
	public void eliminarUniverso(Integer id) throws ResourceNotFoundException {
		//Implementacion Hibernate
		//return universoRepositorioHibernate.findById(id)
				//.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
				
		//Implementacion CRUD
		Universo u = universoRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		u.getSuperheroes().forEach(s -> {
			try {
				ss.eliminarSuperheroe(s.getSuperheroe().getId());
			} catch (ResourceNotFoundException e) {
				Logger logger = Logger.getLogger("Logger");
				logger.log(Level.SEVERE, e.getMessage());
			}
		});
		
		List<SuperheroeUniverso> l = new ArrayList<>();
		
		u.setSuperheroes(l);
		
		//Implementacion Hibernate
		//universoRepositorioHibernate.delete(u);
		
		//Implementacion CRUD
		universoRepositorio.delete(u);
	}

}
