package com.example.demo.service.imp;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Poder;
import com.example.demo.model.SuperheroePoder;
import com.example.demo.model.SuperheroePoderKey;
import com.example.demo.repository.PoderRepositorio;
import com.example.demo.repository.PoderRepositorioHibernate;
import com.example.demo.service.PoderServicio;
import com.example.demo.service.SuperheroePoderServicio;
import com.example.demo.service.SuperheroeServicio;

@Service
public class PoderServicioImp implements PoderServicio{
	
	//Implementacion Hibernate
	@Autowired private PoderRepositorioHibernate poderRepositorioHibernate;
	
	//Implementacion CRUD
	@Autowired private PoderRepositorio poderRepositorio;
	
	@Autowired private SuperheroePoderServicio sps;
	@Autowired private SuperheroeServicio ss;
	
	static final String MENSAJE = "No se ha encontrado el universo con el id ";

	@Override
	public List<Poder> listarPoderes() {
		//Implementacion Hibernate
		return (List<Poder>) poderRepositorioHibernate.findAll();
		
		//Implementacion CRUD
		//return (List<Poder>) poderRepositorio.findAll();
	}

	@Override
	public Poder buscarPoder(Integer id) throws ResourceNotFoundException {
		//Implementacion Hibenate
		//return poderRepositorioHibernate.findById(id)
				//.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		//Implementacion CRUD
		return poderRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
	}

	@Override
	public Poder crearPoder(Poder poder) throws ResourceNotFoundException {
		//Implementacion Hibernate
		//boolean is = poderRepositorioHibernate.findById(poder.getId()).isPresent();
		
		//Implementacion CRUD
		boolean is = poderRepositorio.findById(poder.getId()).isPresent();
		
		Poder p;
		if(is) {
			p = actualizarPoder(poder.getId(), poder);
		}
		else {
			//Implementacion Hibernate
			//p = poderRepositorioHibernate.save(poder);
			
			//Implementacion CRUD
			p = poderRepositorio.save(poder);
		}
		
		return p;
	}

	@Override
	public Poder actualizarPoder(Integer id, Poder poder) throws ResourceNotFoundException {
		//Implementacion Hibenate
		//return poderRepositorioHibernate.findById(id)
				//.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		//Implementacion CRUD
		Poder p = poderRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		p.setNombre(poder.getNombre());
		
		//Implementacion Hibernate
		//return poderRepositorioHibernate.save(p);
		
		//Implementacion CRUD
		return poderRepositorio.save(p);
	}

	@Override
	public void eliminarPoder(Integer id) throws ResourceNotFoundException {
		//Implementacion Hibenate
		//return poderRepositorioHibernate.findById(id)
				//.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		//Implementacion CRUD
		Poder p = poderRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		List<SuperheroePoder> sp = sps.listarSuperheroePoder();
		sp.forEach(superheroes -> {
			if(superheroes.getPoder().getId().equals(id)) {
				SuperheroePoderKey spk = new SuperheroePoderKey(superheroes.getSuperheroe().getId(), id);
				try {
					if(superheroes.getSuperheroe().getPoderes().size() == 1) {
						ss.eliminarSuperheroe(superheroes.getSuperheroe().getId());
					}
					else {
						sps.eliminarSuperheroePoder(spk);
					}
				} catch (ResourceNotFoundException e) {
					Logger logger = Logger.getLogger("Logger");
					logger.log(Level.SEVERE, e.getMessage());
				}
			}
		});
		
		//Implementacion Hibernate
		//poderRepositorioHibernate.delete(p);
		
		//Implementacion CRUD
		poderRepositorio.delete(p);
	}

}
