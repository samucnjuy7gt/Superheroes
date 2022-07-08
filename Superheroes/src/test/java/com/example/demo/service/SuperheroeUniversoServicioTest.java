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
import com.example.demo.model.Superheroe;
import com.example.demo.model.SuperheroeUniverso;
import com.example.demo.model.SuperheroeUniversoKey;
import com.example.demo.model.Universo;
import com.example.demo.repository.SuperheroeUniversoRepositorio;
import com.example.demo.service.imp.SuperheroeUniversoServicioImp;

@ExtendWith(MockitoExtension.class)
class SuperheroeUniversoServicioTest {

	@InjectMocks
	SuperheroeUniversoServicioImp servicio;
	
	@Mock
	SuperheroeUniversoRepositorio sur;
	
	private SuperheroeUniversoKey id;
	
	@BeforeEach
	void setup_test() {
		Universo u = new Universo(1, "The Boys");
		Superheroe s = new Superheroe(1, "Patriota", true, u);
		id = new SuperheroeUniversoKey(s.getId(), u.getId());
	}
	
	@Test
	void test_listarSuperheroeUniverso() {
		
		List<SuperheroeUniverso> expectedResult = new ArrayList<>();
		expectedResult.add(Mockito.mock(SuperheroeUniverso.class));
		
		when(sur.findAll()).thenReturn(expectedResult);
		
		List<SuperheroeUniverso> currentResult = servicio.listarSuperheroeUniverso();
		
		assertThat(currentResult).isNotNull().isNotEmpty();
	}
	
	@Test
	void test_buscarSuperheroeUniverso() throws ResourceNotFoundException{
		
		Universo u = new Universo(1, "The Boys");
		Superheroe s = new Superheroe(1, "Patriota", true, u);
		Optional<SuperheroeUniverso> expectedResult = Optional.ofNullable(new SuperheroeUniverso(s, u));
		
		when(sur.findById(id)).thenReturn(expectedResult);
		
		SuperheroeUniverso currentResult = servicio.buscarSuperheroeUniverso(id);
		
		assertThat(currentResult).isNotNull();
	}
	
	@Test
	void test_crearSuperheroeUniverso() throws ResourceNotFoundException {
		
		Universo u = new Universo(1, "The Boys");
		Superheroe s = new Superheroe(1, "Patriota", true, u);
		SuperheroeUniverso expectedResult = new SuperheroeUniverso(s, u);
		
		when(sur.save(expectedResult)).thenReturn(expectedResult);
		
		SuperheroeUniverso currentResult = servicio.crearSuperheroeUniverso(expectedResult);
		
		assertThat(currentResult).isNotNull();
	}
	
	@Test
	void test_eliminarSuperheroeUniverso() throws ResourceNotFoundException {
		
		Universo u = new Universo(1, "The Boys");
		Superheroe s = new Superheroe(1, "Patriota", true, u);
		Optional<SuperheroeUniverso> expectedResult = Optional.ofNullable(new SuperheroeUniverso(s, u));
		
		when(sur.findById(id)).thenReturn(expectedResult);
		
		servicio.eliminarSuperheroeUniverso(id);
	}
}
