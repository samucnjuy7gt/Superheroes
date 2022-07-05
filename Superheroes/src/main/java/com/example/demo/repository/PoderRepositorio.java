package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Poder;

@Repository
public interface PoderRepositorio extends CrudRepository<Poder, Integer>{

}
