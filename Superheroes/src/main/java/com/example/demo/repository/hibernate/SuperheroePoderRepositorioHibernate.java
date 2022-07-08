package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.SuperheroePoder;
import com.example.demo.model.SuperheroePoderKey;

public interface SuperheroePoderRepositorioHibernate {

	List<SuperheroePoder> findAll();
	
	Optional<SuperheroePoder> findById(SuperheroePoderKey id);
	
	SuperheroePoder save(SuperheroePoder superheroePoder);
	
	void delete(SuperheroePoder superheroePoder);
}
