package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SuperheroePoder")
public class SuperheroePoder implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private SuperheroePoderKey id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "s_p_heroe_id", nullable = false, insertable = false, updatable = false)
	private Superheroe superheroe;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "s_p_poder_id", nullable = false, insertable = false, updatable = false)
	private Poder poder;

	public SuperheroePoderKey getId() {
		return id;
	}

	public void setId(SuperheroePoderKey id) {
		this.id = id;
	}

	public Superheroe getSuperheroe() {
		return superheroe;
	}

	public void setSuperheroe(Superheroe superheroe) {
		this.superheroe = superheroe;
	}

	public Poder getPoder() {
		return poder;
	}

	public void setPoder(Poder poder) {
		this.poder = poder;
	}
}
