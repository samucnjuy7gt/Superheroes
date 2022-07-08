package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Universo;

public interface UniversoRepositorioHibernate {

	List<Universo> findAll();
	
	Optional<Universo> findById(Integer id);
	
	Universo save(Universo universo);
	
	void delete(Universo universo);
}
