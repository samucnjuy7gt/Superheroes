package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Superheroe;

public interface SuperheroeRepositorioHibernate {

	List<Superheroe> findAll();
	
	Optional<Superheroe> findById(Integer id);
	
	Superheroe save(Superheroe superheroe);
	
	void delete(Superheroe superheroe);
}
