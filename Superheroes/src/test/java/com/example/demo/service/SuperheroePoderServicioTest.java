package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Poder;
import com.example.demo.model.Superheroe;
import com.example.demo.model.SuperheroePoder;
import com.example.demo.model.SuperheroePoderKey;
import com.example.demo.model.Universo;
import com.example.demo.repository.SuperheroePoderRepositorio;
import com.example.demo.service.imp.SuperheroePoderServicioImp;

@ExtendWith(MockitoExtension.class)
class SuperheroePoderServicioTest {

	@InjectMocks
	SuperheroePoderServicioImp servicio;
	
	@Mock
	SuperheroePoderRepositorio spr;
	
	private SuperheroePoderKey id;
	
	@BeforeEach
	void setup_test() {
		Poder p = new Poder(1, "Volar");
		Universo u = new Universo(1, "The Boys");
		Superheroe s = new Superheroe(1, "Patriota", true, u);
		id = new SuperheroePoderKey(s.getId(), p.getId());
	}
	
	@Test
	void test_listarSuperheroePoder() {
		
		List<SuperheroePoder> expectedResult = new ArrayList<>();
		expectedResult.add(Mockito.mock(SuperheroePoder.class));
		
		when(spr.findAll()).thenReturn(expectedResult);
		
		List<SuperheroePoder> currentResult = servicio.listarSuperheroePoder();
		
		assertThat(currentResult).isNotNull().isNotEmpty();
	}
	
	@Test
	void test_buscarSuperheroePoder() throws ResourceNotFoundException{
		
		Universo u = new Universo(1, "The Boys");
		Superheroe s = new Superheroe(1, "Patriota", true, u);
		Poder p = new Poder(1, "Volar");
		Optional<SuperheroePoder> expectedResult = Optional.ofNullable(new SuperheroePoder(s, p));
		
		when(spr.findById(id)).thenReturn(expectedResult);
		
		SuperheroePoder currentResult = servicio.buscarSuperheroePoder(id);
		
		assertThat(currentResult).isNotNull();
	}
	
	@Test
	void test_crearSuperheroePoder() throws ResourceNotFoundException {
		
		Universo u = new Universo(1, "The Boys");
		Superheroe s = new Superheroe(1, "Patriota", true, u);
		Poder p = new Poder(1, "Volar");
		SuperheroePoder expectedResult = new SuperheroePoder(s, p);
		
		when(spr.save(expectedResult)).thenReturn(expectedResult);
		
		SuperheroePoder currentResult = servicio.crearSuperheroePoder(expectedResult);
		
		assertThat(currentResult).isNotNull();
	}
	
	@Test
	void test_eliminarSuperheroePoder() throws ResourceNotFoundException {
		
		Universo u = new Universo(1, "The Boys");
		Superheroe s = new Superheroe(1, "Patriota", true, u);
		Poder p = new Poder(1, "Volar");
		Optional<SuperheroePoder> expectedResult = Optional.ofNullable(new SuperheroePoder(s, p));
		
		when(spr.findById(id)).thenReturn(expectedResult);
		
		servicio.eliminarSuperheroePoder(id);
	}
}
