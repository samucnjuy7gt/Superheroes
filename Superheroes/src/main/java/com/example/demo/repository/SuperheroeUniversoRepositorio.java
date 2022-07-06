package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SuperheroeUniverso;
import com.example.demo.model.SuperheroeUniversoKey;

@Repository
public interface SuperheroeUniversoRepositorio extends CrudRepository<SuperheroeUniverso, SuperheroeUniversoKey>{

}
