package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Poder;
import com.example.demo.model.Superheroe;
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
		System.out.println("========================================");
		
		Universo u1 = new Universo(1, "The Boys");
		Universo u2 = new Universo(2, "DC");
		
		Poder p1 = new Poder(1, "Rayos laser");
		Poder p2 = new Poder(2, "Invisibilidad");
		Poder p3 = new Poder(3, "Ser rico");
		
		Superheroe s1 = new Superheroe(1, "Patriota", true, u1);
		Superheroe s2 = new Superheroe(2, "Batman", true, u2);
		Superheroe s3 = new Superheroe(3, "Translucido", false, u1);
		
		poderServicio.crearPoder(p1);
		poderServicio.crearPoder(p2);
		poderServicio.crearPoder(p3);
		
		u1 = universoServicio.crearUniverso(u1);
		u2 = universoServicio.crearUniverso(u2);
		
		s1.setUniverso(u1);
		s2.setUniverso(u2);
		s3.setUniverso(u1);
		
		List<Poder> poderes1 = new ArrayList<>();
		poderes1.add(p1);
		poderes1.add(p3);
		
		List<Poder> poderes2 = new ArrayList<>();
		poderes2.add(p3);
		
		List<Poder> poderes3 = new ArrayList<>();
		poderes3.add(p2);
		
		s1 = superheroeServicio.crearSuperheroe(s1, poderes1);
		s2 = superheroeServicio.crearSuperheroe(s2, poderes2);
		s3 = superheroeServicio.crearSuperheroe(s3, poderes3);
		
		System.out.println(superheroeServicio.buscarSuperheroe(1).getUniverso().getSuperheroes().size());
		System.out.println(superheroeServicio.buscarSuperheroe(2).getUniverso().getSuperheroes().size());
		System.out.println(superheroeServicio.buscarSuperheroe(3).getUniverso().getSuperheroes().size());
		
		//superheroeServicio.eliminarSuperheroe(s3.getId());

		List<SuperheroeUniverso> superheroes = universoServicio.buscarUniverso(u1.getId()).getSuperheroes();
		for(SuperheroeUniverso pAux: superheroes){
			System.out.println(pAux.getSuperheroe().getNombre());
		}
		
		//Scanner scanner = new Scanner(System.in);
		//String s = scanner.nextLine();
	}
}
