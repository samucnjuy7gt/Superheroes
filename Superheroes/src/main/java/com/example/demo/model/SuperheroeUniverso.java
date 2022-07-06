package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SuperheroeUniverso")
public class SuperheroeUniverso implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private SuperheroeUniversoKey id;
	
	@ManyToOne()
	@JoinColumn(name = "s_u_heroe_id", nullable = false, insertable = false, updatable = false)
	private Superheroe superheroe;
	
	@ManyToOne()
	@JoinColumn(name = "s_u_universo_id", nullable = false, insertable = false, updatable = false)
	private Universo universo;
	
	public SuperheroeUniverso() {
		
	}
	
	public SuperheroeUniverso(Superheroe superheroe, Universo universo) {
		super();
		this.superheroe = superheroe;
		this.universo = universo;
		
		id = new SuperheroeUniversoKey(superheroe.getId(), universo.getId());
	}

	public SuperheroeUniversoKey getId() {
		return id;
	}

	public void setId(SuperheroeUniversoKey id) {
		this.id = id;
	}

	public Superheroe getSuperheroe() {
		return superheroe;
	}

	public void setSuperheroe(Superheroe superheroe) {
		this.superheroe = superheroe;
	}

	public Universo getUniverso() {
		return universo;
	}

	public void setUniverso(Universo universo) {
		this.universo = universo;
	}
}
