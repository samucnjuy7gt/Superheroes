package com.example.demo.service.imp;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Poder;
import com.example.demo.model.Superheroe;
import com.example.demo.model.SuperheroePoder;
import com.example.demo.model.SuperheroePoderKey;
import com.example.demo.model.SuperheroeUniverso;
import com.example.demo.model.SuperheroeUniversoKey;
import com.example.demo.repository.SuperheroeRepositorio;
import com.example.demo.service.SuperheroePoderServicio;
//import com.example.demo.repository.SuperheroeRepositorioHibernate;
import com.example.demo.service.SuperheroeServicio;
import com.example.demo.service.SuperheroeUniversoServicio;

@Service
public class SuperheroeServicioImp implements SuperheroeServicio{
	
	//Implementacion Hibernate
	//@Autowired private SuperheroeRepositorioHibernate superheroeRepositorioHibernate;
	
	//Implementacion CRUD
	@Autowired private SuperheroeRepositorio superheroeRepositorio;
	
	@Autowired private SuperheroeUniversoServicio sus;
	@Autowired private SuperheroePoderServicio spc;
	
	static final String MENSAJE = "No se ha encontrado el superheroe con el id ";

	@Override
	public List<Superheroe> listarSuperheroes() {
		//Implementacion Hibernate
		//return superheroeRepositorioHibernate.findAll();
		
		//Implementacion CRUD
		return (List<Superheroe>) superheroeRepositorio.findAll();
	}

	@Override
	public Superheroe buscarSuperheroe(Integer id) throws ResourceNotFoundException{
		//Implementacion Hibernate
		//return superheroeRepositorioHibernate.findById(id)
				//.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		//Implementacion CURD
		return superheroeRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
	}

	@Override
	public List<Superheroe> buscarSuperheroeNombre(String nombre) {
		//Implementacion CRUD && Hibernate
		List<Superheroe> resultado = new ArrayList<>();
		List<Superheroe> supers = listarSuperheroes();
		supers.forEach(s -> {
			if(s.getNombre().contains(nombre)) {
				resultado.add(s);
			}
		});
		
		return resultado;
	}

	@Override
	public Superheroe crearSuperheroe(Superheroe superheroe, List<Poder> poderes) throws ResourceNotFoundException {	
		//Implementacion Hibernate
		//boolean is = superheroeRepositorioHibernate.findById(superheroe.getId()).isPresent();
		
		//Implementacion CRUD
		boolean is = superheroeRepositorio.findById(superheroe.getId()).isPresent();
		
		Superheroe s;
		if(is) {
			s = actualizarSuperheroe(superheroe.getId(), superheroe);
		}
		else {
			//Implementacion Hibernate
			//s = superheroeRepositorioHibernate.save(superheroe);
			
			//Implementacion CRUD
			s = superheroeRepositorio.save(superheroe);
			
			SuperheroeUniverso su = new SuperheroeUniverso(s, s.getUniverso());
			sus.crearSuperheroeUniverso(su);
			
			poderes.forEach(p -> {
				SuperheroePoder sp = new SuperheroePoder(s, p);
				spc.crearSuperheroePoder(sp);
			});
		}
		
		return s;
	}
	
	@Override
	public Superheroe actualizarSuperheroe(Integer id, Superheroe superheroe) throws ResourceNotFoundException {
		//Implementacion Hibernate
		//Superheroe superH = superheroeRepositorioHibernate.findById(id)
				//.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
		
		//Implementacion CRUD
		Superheroe superH = superheroeRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
		
		superH.setNombre(superheroe.getNombre());
		superH.setVivo(superheroe.isVivo());
		
		//Implementacion Hibernate
		//return superheroeRepositorioHibernate.save(superH);
		
		//Implementacion CRUD
		return superheroeRepositorio.save(superH);
	}

	@Override
	public void eliminarSuperheroe(Integer id) throws ResourceNotFoundException {
		//Implementacion Hibernate
		//Superheroe superH = superheroeRepositorioHibernate.findById(id)
				//.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
		
		//Implementacion CRUD
		Superheroe superH = superheroeRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
		
		SuperheroeUniversoKey suk = new SuperheroeUniversoKey(superH.getId(), superH.getUniverso().getId());
		sus.eliminarSuperheroeUniverso(suk);
		
		superH.getPoderes().forEach(p -> {
			SuperheroePoderKey spk = new SuperheroePoderKey(superH.getId(), p.getPoder().getId());
			try {
				spc.eliminarSuperheroePoder(spk);
			} catch (ResourceNotFoundException e) {
				Logger logger = Logger.getLogger("Logger");
				logger.log(Level.SEVERE, e.getMessage());
			}
		});
		
		List<SuperheroePoder> l = new ArrayList<>();
		
		superH.setPoderes(l);
		
		//Implementacion Hibernate
		//superheroeRepositorioHibernate.delete(superH);
		
		//Implementacion CRUD
		superheroeRepositorio.delete(superH);
	}

	@Override
	public Superheroe matarSuperheroe(Integer id) throws ResourceNotFoundException {
		//Implementacion Hibernate
		//Superheroe superH = superheroeRepositorioHibernate.findById(id)
				//.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
		
		//Implementacion CRUD
		Superheroe superheroe = superheroeRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
		
		superheroe.setVivo(false);
		
		//Implementacion Hibernate
		//return superheroeRepositorioHibernate.save(superheroe);
		
		//Implementacion CRUD
		return superheroeRepositorio.save(superheroe);
	}

	@Override
	public Superheroe revivirSuperheroe(Integer id) throws ResourceNotFoundException{
		//Implementacion Hibernate
		//Superheroe superH = superheroeRepositorioHibernate.findById(id)
				//.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));

		//Implementacion CRUD
		Superheroe superheroe = superheroeRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
		
		superheroe.setVivo(true);
		
		//Implementacion Hibernate
		//return superheroeRepositorioHibernate.save(superheroe);
				
		//Implementacion CRUD
		return superheroeRepositorio.save(superheroe);
	}
}
