package com.example.demo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Poder;
import com.example.demo.model.Superheroe;
import com.example.demo.model.SuperheroePoder;
import com.example.demo.model.SuperheroeUniverso;
import com.example.demo.model.SuperheroeUniversoKey;
import com.example.demo.repository.SuperheroeRepositorio;
import com.example.demo.service.SuperheroePoderServicio;
//import com.example.demo.repository.SuperheroeRepositorioHibernate;
import com.example.demo.service.SuperheroeServicio;
import com.example.demo.service.SuperheroeUniversoServicio;

@Service
public class SuperheroeServicioImp implements SuperheroeServicio{
	
	//Implementacion CRUD
	@Autowired private SuperheroeRepositorio superheroeRepositorio;
	@Autowired private SuperheroeUniversoServicio sus;
	@Autowired private SuperheroePoderServicio spc;
	
	//Implementacion Hibernate
	//@Autowired
	//private SuperheroeRepositorioHibernate superheroeRepositorioHibernate;
	
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
		//return superheroeRepositorio.findById(id)
				//.orElseThrow(() -> new ResourceNotFoundException(MENSAJE));
		
		//Implementacion CURD
		return superheroeRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
	}

	@Override
	public List<Superheroe> buscarSuperheroeNombre(String nombre) {
		return null;
	}

	@Override
	public Superheroe crearSuperheroe(Superheroe superheroe, List<Poder> poderes) throws ResourceNotFoundException {
		//Implementacion Hibernate
		//return superheroeRepositorioHibernate.save(superheroe);
		
		//Implementacion CRUD
		boolean is = superheroeRepositorio.findById(superheroe.getId()).isPresent();
		
		Superheroe s;
		if(is) {
			s = actualizarSuperheroe(superheroe.getId(), superheroe);
		}
		else {
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
	public Superheroe actualizarSuperheroe(Integer id,Superheroe superheroe) throws ResourceNotFoundException{
		//Implementacion Hibernate
		//Superheroe superH = superheroeRepositorioHibernate.findById(id)
				//.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
		//superH.setNombre(superheroe.getNombre());
		//superH.setVivo(superheroe.isVivo());
		
		//return superheroeRepositorioHibernate.save(superH);
		
		//Implementacion CRUD
		Superheroe superH = superheroeRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
		
		superH.setNombre(superheroe.getNombre());
		superH.setVivo(superheroe.isVivo());
		
		return superheroeRepositorio.save(superH);
		
	}

	@Override
	public void eliminarSuperheroe(Integer id) throws ResourceNotFoundException{
		//Implementacion Hibernate
		//Superheroe superH = superheroeRepositorioHibernate.findById(id)
				//.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
		
		//superheroeRepositorioHibernate.delete(superH);
		
		//Implementacion CRUD
		Superheroe superH = superheroeRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
		
		SuperheroeUniversoKey suk = new SuperheroeUniversoKey(superH.getId(), superH.getUniverso().getId());
		sus.eliminarSuperheroeUniverso(suk);
		
		superheroeRepositorio.delete(superH);
	}

	@Override
	public Superheroe matarSuperheroe(Integer id) throws ResourceNotFoundException{
		//Implementacion Hibernate
		//Superheroe superH = superheroeRepositorioHibernate.findById(id)
				//.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
		
		//superheroe.setVivo(false);
		
		//superheroeRepositorioHibernate.save(superH);
		
		//Implementacion CRUD
		Superheroe superheroe = superheroeRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
		
		superheroe.setVivo(false);
		
		return superheroeRepositorio.save(superheroe);
	}

	@Override
	public Superheroe revivirSuperheroe(Integer id) throws ResourceNotFoundException{
		//Implementacion Hibernate
		//Superheroe superH = superheroeRepositorioHibernate.findById(id)
				//.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
		
		//superheroe.setVivo(true);
		
		//superheroeRepositorioHibernate.save(superH);
		
		//Implementacion CRUD
		Superheroe superheroe = superheroeRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MENSAJE + id));
		
		superheroe.setVivo(true);
		
		return superheroeRepositorio.save(superheroe);
	}
}
