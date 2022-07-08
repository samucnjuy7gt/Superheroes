package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Poder;

public interface PoderRepositorioHibernate {

	List<Poder> findAll();
	
	Optional<Poder> findById(Integer id);
	
	Poder save(Poder poder);
	
	void delete(Poder poder);
}
