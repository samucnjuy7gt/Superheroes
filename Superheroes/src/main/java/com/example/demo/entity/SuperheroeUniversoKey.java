package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SuperheroeUniversoKey implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "s_u_heroe_id")
	private Integer heroeId;
	
	@Column(name = "s_u_universo_id")
	private Integer universoId;

	public Integer getHeroeId() {
		return heroeId;
	}

	public void setHeroeId(Integer heroeId) {
		this.heroeId = heroeId;
	}

	public Integer getUniversoId() {
		return universoId;
	}

	public void setUniversoId(Integer universoId) {
		this.universoId = universoId;
	}
}
