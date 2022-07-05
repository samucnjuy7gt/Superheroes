package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Universo;

@Repository
public interface UniversoRepositorio extends CrudRepository<Universo, Integer>{

}
