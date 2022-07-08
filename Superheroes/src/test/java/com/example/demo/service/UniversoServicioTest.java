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
import com.example.demo.model.Universo;
import com.example.demo.repository.UniversoRepositorio;
import com.example.demo.service.imp.UniversoServicioImp;

@ExtendWith(MockitoExtension.class)
class UniversoServicioTest {

	@InjectMocks
	UniversoServicioImp servicio;
	
	@Mock
	UniversoRepositorio ur;
	@Mock
	SuperheroeServicio ss;
	
	private Integer id;
	
	@BeforeEach
	void setup_test() {
		id = Integer.valueOf(1);
	}
	
	@Test
	void test_listarUniversos() {
		
		List<Universo> expectedResult = new ArrayList<>();
		expectedResult.add(Mockito.mock(Universo.class));
		
		when(ur.findAll()).thenReturn(expectedResult);
		
		List<Universo> currentResult = servicio.listarUniversos();
		
		assertThat(currentResult).isNotNull().isNotEmpty();
	}
	
	@Test
	void test_buscarUniverso() throws ResourceNotFoundException{
		
		Optional<Universo> expectedResult = Optional.ofNullable(new Universo(1, "The Boys"));
		
		when(ur.findById(1)).thenReturn(expectedResult);
		
		Universo currentResult = servicio.buscarUniverso(1);
		
		assertThat(currentResult).isNotNull();
	}
	
	@Test
	void test_crearUniverso() throws ResourceNotFoundException {
		
		Universo expectedResult = new Universo(1, "The Boys");
		
		when(ur.save(expectedResult)).thenReturn(expectedResult);
		
		Universo currentResult = servicio.crearUniverso(expectedResult);
		
		assertThat(currentResult).isNotNull();
	}
	
	@Test
	void test_actualizarUniverso() throws ResourceNotFoundException {
		
		Optional<Universo> expectedResult = Optional.ofNullable(new Universo(1, "The Boys"));
		
		when(ur.findById(1)).thenReturn(expectedResult);
		
		servicio.actualizarUniverso(1, new Universo(1, "The Boys"));
	}
	
	@Test
	void test_eliminarUniverso() throws ResourceNotFoundException {
		Optional<Universo> expectedResult = Optional.ofNullable(new Universo(1, "The Boys"));
		
		when(ur.findById(1)).thenReturn(expectedResult);
		
		servicio.eliminarUniverso(id);
	}
	
	//@Test
	//void test_eliminarUniverso_eliminarSuper() throws ResourceNotFoundException {
	//	Optional<Universo> u = Optional.ofNullable(new Universo(1, "The Boys"));
	//	Superheroe s = new Superheroe(1, "Patriota", true, u.get());
	//	SuperheroeUniversoKey suk = new SuperheroeUniversoKey(s.getId(), u.get().getId());
	//	SuperheroeUniverso expectedResult = new SuperheroeUniverso(s, u.get());
	//	
	//	sus.eliminarSuperheroeUniverso(suk);
	//	
	//	servicio.eliminarUniverso(id);
	//}
}
