package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.model.Poder;

public class PoderDTO implements Serializable{

	private Integer id;
	private String nombre;
	
	public PoderDTO(Poder poder) {
		id = poder.getId();
		nombre = poder.getNombre();
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
}
