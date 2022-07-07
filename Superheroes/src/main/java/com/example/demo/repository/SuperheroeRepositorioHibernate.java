package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Superheroe;

public interface SuperheroeRepositorioHibernate {

	//Pasar un String por parametro con el nombre de la tabla
	List<Superheroe> findAll();
	
	Optional<Superheroe> findById(Integer id);
	
	Superheroe save(Superheroe objeto);
	
	void delete(Superheroe objeto);
}
