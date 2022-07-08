package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SuperheroePoderKey implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "s_p_heroe_id")
	private Integer heroeId;
	
	@Column(name = "s_p_poder_id")
	private Integer poderId;
	
	public SuperheroePoderKey() {
		
	}
	
	public SuperheroePoderKey(Integer heroeId, Integer poderId) {
		super();
		this.heroeId = heroeId;
		this.poderId = poderId;
	}

	public Integer getHeroeId() {
		return heroeId;
	}

	public void setHeroeId(Integer heroeId) {
		this.heroeId = heroeId;
	}

	public Integer getPoderId() {
		return poderId;
	}

	public void setPoderId(Integer poderId) {
		this.poderId = poderId;
	}
}
