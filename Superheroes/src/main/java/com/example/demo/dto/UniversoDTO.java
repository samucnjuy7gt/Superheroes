package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;

import com.example.demo.model.Universo;

public class UniversoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nombre;
	private List<Integer> superheroesId;
	private List<String> superheroesNombre;
	private List<Boolean> superheroesVivo;
	private List<Integer> superheroesPoderesId;
	private List<String> superherpesPoderesNombre;
	
	public UniversoDTO(Universo universo) {
		id = universo.getId();
		nombre = universo.getNombre();
		
		universo.getSuperheroes().forEach(s -> {
			superheroesId.add(s.getSuperheroe().getId());
			superheroesNombre.add(s.getSuperheroe().getNombre());
			superheroesVivo.add(s.getSuperheroe().isVivo());
			
			s.getSuperheroe().getPoderes().forEach(p -> {
				superheroesPoderesId.add(p.getPoder().getId());
				superherpesPoderesNombre.add(p.getPoder().getNombre());
			});
		});
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Integer> getSuperheroesId() {
		return superheroesId;
	}

	public void setSuperheroesId(List<Integer> superheroesId) {
		this.superheroesId = superheroesId;
	}

	public List<String> getSuperheroesNombre() {
		return superheroesNombre;
	}

	public void setSuperheroesNombre(List<String> superheroesNombre) {
		this.superheroesNombre = superheroesNombre;
	}

	public List<Boolean> getSuperheroesVivo() {
		return superheroesVivo;
	}

	public void setSuperheroesVivo(List<Boolean> superheroesVivo) {
		this.superheroesVivo = superheroesVivo;
	}

	public List<Integer> getSuperheroesPoderesId() {
		return superheroesPoderesId;
	}

	public void setSuperheroesPoderesId(List<Integer> superheroesPoderesId) {
		this.superheroesPoderesId = superheroesPoderesId;
	}

	public List<String> getSuperherpesPoderesNombre() {
		return superherpesPoderesNombre;
	}

	public void setSuperherpesPoderesNombre(List<String> superherpesPoderesNombre) {
		this.superherpesPoderesNombre = superherpesPoderesNombre;
	}
}
