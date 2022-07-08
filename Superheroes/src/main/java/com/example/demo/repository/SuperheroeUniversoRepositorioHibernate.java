package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.SuperheroeUniverso;
import com.example.demo.model.SuperheroeUniversoKey;

public interface SuperheroeUniversoRepositorioHibernate {

	List<SuperheroeUniverso> findAll();
	
	Optional<SuperheroeUniverso> findById(SuperheroeUniversoKey id);
	
	SuperheroeUniverso save(SuperheroeUniverso superheroeUniverso);
	
	void delete(SuperheroeUniverso superheroeUniverso);
}
