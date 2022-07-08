package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Poder;
import com.example.demo.model.Superheroe;
import com.example.demo.model.SuperheroePoder;
import com.example.demo.model.SuperheroeUniverso;
import com.example.demo.model.Universo;
import com.example.demo.service.PoderServicio;
import com.example.demo.service.SuperheroeServicio;
import com.example.demo.service.UniversoServicio;

@SpringBootApplication
public class SuperheroesApplication implements CommandLineRunner{
	
	@Autowired private SuperheroeServicio superheroeServicio;
	@Autowired private PoderServicio poderServicio;
	@Autowired private UniversoServicio universoServicio;

	public static void main(String[] args) {
		SpringApplication.run(SuperheroesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Universo u1 = new Universo(1, "The Boys");
		Universo u2 = new Universo(2, "DC");
		Universo u3 = new Universo(3, "Marvel");
		
		Poder p1 = new Poder(1, "Rayos laser");
		Poder p2 = new Poder(2, "Invisibilidad");
		Poder p3 = new Poder(3, "Ser rico");
		Poder p4 = new Poder(4, "Volar");
		Poder p5 = new Poder(5, "Super fuerza");
		
		Superheroe s1 = new Superheroe(1, "Patriota", true, u1);
		Superheroe s2 = new Superheroe(2, "Batman", true, u2);
		Superheroe s3 = new Superheroe(3, "Translucido", false, u1);
		Superheroe s4 = new Superheroe(4, "Superman", true, u2);
		Superheroe s5 = new Superheroe(5, "Soldier Boy", true, u1);
		Superheroe s6 = new Superheroe(6, "Iron Man", false, u3);
		
		poderServicio.crearPoder(p1);
		poderServicio.crearPoder(p2);
		poderServicio.crearPoder(p3);
		poderServicio.crearPoder(p4);
		poderServicio.crearPoder(p5);
		
		u1 = universoServicio.crearUniverso(u1);
		u2 = universoServicio.crearUniverso(u2);
		u3 = universoServicio.crearUniverso(u3);
		
		s1.setUniverso(u1);
		s2.setUniverso(u2);
		s3.setUniverso(u1);
		s4.setUniverso(u2);
		s5.setUniverso(u1);
		s6.setUniverso(u3);
		
		List<Poder> poderes1 = new ArrayList<>();
		poderes1.add(p1);
		poderes1.add(p3);
		poderes1.add(p4);
		poderes1.add(p5);
		
		List<Poder> poderes2 = new ArrayList<>();
		poderes2.add(p3);
		
		List<Poder> poderes3 = new ArrayList<>();
		poderes3.add(p2);
		
		List<Poder> poderes4 = new ArrayList<>();
		poderes4.add(p1);
		poderes4.add(p4);
		poderes4.add(p5);
		
		List<Poder> poderes5 = new ArrayList<>();
		poderes5.add(p5);
		
		List<Poder> poderes6 = new ArrayList<>();
		poderes6.add(p3);
		
		s1 = superheroeServicio.crearSuperheroe(s1, poderes1);
		s2 = superheroeServicio.crearSuperheroe(s2, poderes2);
		s3 = superheroeServicio.crearSuperheroe(s3, poderes3);
		s4 = superheroeServicio.crearSuperheroe(s4, poderes4);
		s5 = superheroeServicio.crearSuperheroe(s5, poderes5);
		s6 = superheroeServicio.crearSuperheroe(s6, poderes6);
		
		System.out.println(superheroeServicio.buscarSuperheroe(s1.getId()).getUniverso().getSuperheroes().size());
		System.out.println(superheroeServicio.buscarSuperheroe(s2.getId()).getUniverso().getSuperheroes().size());
		System.out.println(superheroeServicio.buscarSuperheroe(s3.getId()).getUniverso().getSuperheroes().size());
		System.out.println(superheroeServicio.buscarSuperheroe(s4.getId()).getUniverso().getSuperheroes().size());
		System.out.println(superheroeServicio.buscarSuperheroe(s5.getId()).getUniverso().getSuperheroes().size());
		System.out.println(superheroeServicio.buscarSuperheroe(s6.getId()).getUniverso().getSuperheroes().size());
		
		List<Superheroe> supers = superheroeServicio.buscarSuperheroeNombre("n");
		for(Superheroe pAux: supers){
			System.out.println("Superheroes que contienen n: " + pAux.getNombre());
		}
		
		superheroeServicio.eliminarSuperheroe(s3.getId());
		
		universoServicio.eliminarUniverso(u2.getId());
		
		poderServicio.eliminarPoder(superheroeServicio.buscarSuperheroe(s1.getId()).getPoderes().get(1).getPoder().getId());
		
		List<SuperheroePoder> ps1 = superheroeServicio.buscarSuperheroe(s1.getId()).getPoderes();
		for(SuperheroePoder pAux: ps1){
			System.out.println("Poder de " + s1.getNombre() + ": " + pAux.getPoder().getNombre());
		}
		
		List<SuperheroePoder> ps5 = superheroeServicio.buscarSuperheroe(s5.getId()).getPoderes();
		for(SuperheroePoder pAux: ps5){
			System.out.println("Poder de " + s5.getNombre() + ": " + pAux.getPoder().getNombre());
		}

		List<SuperheroeUniverso> superheroes = universoServicio.buscarUniverso(u1.getId()).getSuperheroes();
		for(SuperheroeUniverso pAux: superheroes){
			System.out.println(pAux.getSuperheroe().getNombre());
		}
	}
}
