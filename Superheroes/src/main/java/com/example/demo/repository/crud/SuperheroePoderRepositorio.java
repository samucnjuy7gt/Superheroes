package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SuperheroePoder;
import com.example.demo.model.SuperheroePoderKey;

@Repository
public interface SuperheroePoderRepositorio extends CrudRepository<SuperheroePoder, SuperheroePoderKey>{

}
