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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Superheroe")
public class Superheroe implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "vivo", nullable = false)
	private boolean vivo;
	
	//Mapeo plano
	//@Column(name = "heroe_universo_id", nullable = false)
	//private Integer heroe_universo_id
	
	@ManyToOne()
	@JoinColumn(name = "universo_id", nullable = false)
	private Universo universo;
	
	@OneToMany()
	@JoinColumn(name = "s_p_heroe_id")
	private List<SuperheroePoder> poderes;
	
	public Superheroe() {
		
	}
	
	public Superheroe(Integer id, String nombre, boolean vivo, Universo universo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.vivo = vivo;
		this.universo = universo;
		this.poderes = new ArrayList<>();
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

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	public Universo getUniverso() {
		return universo;
	}

	public void setUniverso(Universo universo) {
		this.universo = universo;
	}

	public List<SuperheroePoder> getPoderes() {
		return poderes;
	}
	
	public void setPoderes(List<SuperheroePoder> poderes) {
		this.poderes = poderes;
	}

	public void addPoder(SuperheroePoder poder) {
		poderes.add(poder);
	}
}
