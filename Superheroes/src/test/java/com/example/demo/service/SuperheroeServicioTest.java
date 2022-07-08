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
import com.example.demo.model.Universo;
import com.example.demo.repository.SuperheroeRepositorio;
import com.example.demo.service.imp.SuperheroeServicioImp;

@ExtendWith(MockitoExtension.class)
class SuperheroeServicioTest {

	@InjectMocks
	SuperheroeServicioImp servicio;
	
	@Mock
	SuperheroeRepositorio sr;
	@Mock
	SuperheroeUniversoServicio sus;
	@Mock
	SuperheroePoderServicio sps;
	
	private Integer id;
	
	@BeforeEach
	void setup_test() {
		id = Integer.valueOf(1);
	}
	
	@Test
	void test_listarSuperheroes() {
		
		List<Superheroe> expectedResult = new ArrayList<>();
		expectedResult.add(Mockito.mock(Superheroe.class));
		
		when(sr.findAll()).thenReturn(expectedResult);
		
		List<Superheroe> currentResult = servicio.listarSuperheroes();
		
		assertThat(currentResult).isNotNull().isNotEmpty();
	}
	
	@Test
	void test_buscarSuperheroe() throws ResourceNotFoundException{
		
		Universo u = new Universo(1, "The Boys");
		Optional<Superheroe> expectedResult = Optional.ofNullable(new Superheroe(1, "Patriota", true, u));
		
		when(sr.findById(1)).thenReturn(expectedResult);
		
		Superheroe currentResult = servicio.buscarSuperheroe(1);
		
		assertThat(currentResult).isNotNull();
	}
	
	@Test
	void test_superheroeNombre_noCoincide() {
		
		List<Superheroe> expectedResult = new ArrayList<>();
		
		when(servicio.listarSuperheroes()).thenReturn(expectedResult);
		
		List<Superheroe> currentResult = servicio.buscarSuperheroeNombre("Patriota");
		
		assertThat(currentResult).isNotNull();
	}
	
	@Test
	void test_superheroeNombre_coincide() {
		
		List<Superheroe> expectedResult = new ArrayList<>();
		Universo u = new Universo(1, "The Boys");
		Superheroe s = new Superheroe(1, "Patriota", true, u);
		expectedResult.add(s);
		
		when(servicio.buscarSuperheroeNombre("Patriota")).thenReturn(expectedResult);
		
		List<Superheroe> currentResult = servicio.buscarSuperheroeNombre("Patriota");
		
		assertThat(currentResult).isNotNull();
	}
	
	@Test
	void test_crearSuperheroe() throws ResourceNotFoundException {
		
		Universo u = new Universo(1, "The Boys");
		Superheroe expectedResult = new Superheroe(1, "Patriota", true, u);
		Poder p1 = new Poder(1, "Volar");
		List<Poder> ps = new ArrayList<>();
		ps.add(p1);
		
		when(sr.save(expectedResult)).thenReturn(expectedResult);
		
		Superheroe currentResult = servicio.crearSuperheroe(expectedResult, ps);
		
		assertThat(currentResult).isNotNull();
	}
	
	//@Test
	//void test_crearSuperheroe_actualiza() throws ResourceNotFoundException {
	//	
	//	Universo u = new Universo(1, "The Boys");
	//	Superheroe expectedResult = new Superheroe(1, "Patriota", true, u);
	//	Poder p1 = new Poder(1, "Volar");
	//	List<Poder> ps = new ArrayList<>();
	//	ps.add(p1);
	//	
	//	when(servicio.actualizarSuperheroe(expectedResult.getId(), expectedResult)).thenReturn(expectedResult);
	//	
	//	Superheroe currentResult = servicio.crearSuperheroe(expectedResult, ps);
	//	
	//	assertThat(currentResult).isNotNull();
	//}
	
	@Test
	void test_actualizarSuperheroe() throws ResourceNotFoundException {
		
		Universo u = new Universo(1, "The Boys");
		Optional<Superheroe> expectedResult = Optional.ofNullable(new Superheroe(1, "Patriota", true, u));
		
		when(sr.findById(1)).thenReturn(expectedResult);
		
		servicio.actualizarSuperheroe(1, new Superheroe(1, "Patriota", true, u));
    
		//assertThat(currentResult).isNotNull();
	}
	
	@Test
	void test_eliminarSuperheroe() throws ResourceNotFoundException {
		
		Universo u = new Universo(1, "The Boys");
		Optional<Superheroe> expectedResult = Optional.ofNullable(new Superheroe(1, "Patriota", true, u));
		
		when(sr.findById(1)).thenReturn(expectedResult);
		
		servicio.eliminarSuperheroe(id);
	}
	
	//@Test
	//void test_eliminarSuperheroe() throws ResourceNotFoundException {
	//	
	//	Universo u = new Universo(1, "The Boys");
	//	Superheroe expectedResult = new Superheroe(1, "Patriota", true, u);
	//	
	//	servicio.eliminarSuperheroe(expectedResult.getId());
	//	
	//}
	
	@Test
	void test_matarSuperheroe() throws ResourceNotFoundException {
		
		Universo u = new Universo(1, "The Boys");
		Optional<Superheroe> expectedResult = Optional.ofNullable(new Superheroe(1, "Patriota", false, u));
		
		when(sr.findById(id)).thenReturn(expectedResult);
		
		expectedResult.get().setVivo(false);
		
		servicio.matarSuperheroe(1);
	}
	
	@Test
	void test_revivirSuperheroe() throws ResourceNotFoundException {
		
		Universo u = new Universo(1, "The Boys");
		Optional<Superheroe> expectedResult = Optional.ofNullable(new Superheroe(1, "Patriota", true, u));
		
		when(sr.findById(1)).thenReturn(expectedResult);
		expectedResult.get().setVivo(true);
		
		Superheroe currentResult = servicio.buscarSuperheroe(1);
		currentResult = servicio.revivirSuperheroe(currentResult.getId());
		
	}
}
