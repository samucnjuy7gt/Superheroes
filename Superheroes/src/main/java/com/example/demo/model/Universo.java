package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	//Mapeo plano
	//@Column(name = "universo_heroe_id")
	//private Integer universoHeroeId
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "universo_heroe_id")
	private Superheroe superheroe;
	
	@OneToMany()
	@JoinColumn(name = "s_u_universo_id")
	private List<SuperheroeUniverso> superheroes;

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

	public Superheroe getSuperheroe() {
		return superheroe;
	}

	public void setSuperheroes(Superheroe superheroe) {
		this.superheroe = superheroe;
	}
}
