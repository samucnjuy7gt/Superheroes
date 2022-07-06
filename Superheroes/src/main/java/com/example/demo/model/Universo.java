package com.example.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Universo")
public class Universo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@OneToMany()
	@JoinColumn(name = "s_u_universo_id")
	private List<SuperheroeUniverso> superheroes;
	
	public Universo() {
		
	}
	
	public Universo(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
		superheroes = new ArrayList<>();
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

	public List<SuperheroeUniverso> getSuperheroes() {
		return superheroes;
	}

	public void addSuperheroe(SuperheroeUniverso su) {
		superheroes.add(su);
	}
}
