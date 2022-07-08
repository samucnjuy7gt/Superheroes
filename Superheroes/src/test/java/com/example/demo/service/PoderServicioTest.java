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
import com.example.demo.repository.PoderRepositorio;
import com.example.demo.service.imp.PoderServicioImp;

@ExtendWith(MockitoExtension.class)
public class PoderServicioTest {

	@InjectMocks
	PoderServicioImp servicio;
	
	@Mock
	PoderRepositorio pr;
	@Mock
	SuperheroePoderServicio sps;
	@Mock
	SuperheroeServicio ss;
	
	private Integer id;
	
	@BeforeEach
	void setup_test() {
		id = Integer.valueOf(1);
	}
	
	@Test
	void test_listarPoderes() {
		
		List<Poder> expectedResult = new ArrayList<>();
		expectedResult.add(Mockito.mock(Poder.class));
		
		when(pr.findAll()).thenReturn(expectedResult);
		
		List<Poder> currentResult = servicio.listarPoderes();
		
		assertThat(currentResult).isNotNull();
		assertThat(currentResult).isNotEmpty();
	}
	
	@Test
	void test_buscarPoder() throws ResourceNotFoundException{
		
		Optional<Poder> expectedResult = Optional.ofNullable(new Poder(1, "Volar"));
		
		when(pr.findById(1)).thenReturn(expectedResult);
		
		Poder currentResult = servicio.buscarPoder(1);
		
		assertThat(currentResult).isNotNull();
	}
	
	@Test
	void test_crearPoder() throws ResourceNotFoundException {
		
		Poder expectedResult = new Poder(1, "Volar");
		
		when(pr.save(expectedResult)).thenReturn(expectedResult);
		
		Poder currentResult = servicio.crearPoder(expectedResult);
		
		assertThat(currentResult).isNotNull();
	}
	
	@Test
	void test_actualizarPoder() throws ResourceNotFoundException {
		
		Optional<Poder> expectedResult = Optional.ofNullable(new Poder(1, "Volar"));
		
		when(pr.findById(1)).thenReturn(expectedResult);
		
		servicio.actualizarPoder(1, new Poder(1, "Volar"));
	}
	
	@Test
	void test_eliminarPoder() throws ResourceNotFoundException {
		
		Optional<Poder> expectedResult = Optional.ofNullable(new Poder(1, "Volar"));
		
		when(pr.findById(1)).thenReturn(expectedResult);
		
		servicio.eliminarPoder(id);
	}
}
