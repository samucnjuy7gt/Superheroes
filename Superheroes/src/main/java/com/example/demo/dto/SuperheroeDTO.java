package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;

import com.example.demo.model.Superheroe;

public class SuperheroeDTO implements Serializable{

	private Integer id;
	private String nombre;
	private boolean vivo;
	private Integer universoId;
	private String universoNombre;
	private List<Integer> poderesId;
	private List<String> poderesNombre;
	
	public SuperheroeDTO(Superheroe superheroe) {
		id = superheroe.getId();
		nombre = superheroe.getNombre();
		vivo = superheroe.isVivo();
		universoId = superheroe.getUniverso().getId();
		universoNombre = superheroe.getUniverso().getNombre();
		
		superheroe.getPoderes().forEach(p -> {
			poderesId.add(p.getPoder().getId());
			poderesNombre.add(p.getPoder().getNombre());
		});
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUniversoId() {
		return universoId;
	}

	public void setUniversoId(Integer universoId) {
		this.universoId = universoId;
	}

	public String getUniversoNombre() {
		return universoNombre;
	}

	public void setUniversoNombre(String universoNombre) {
		this.universoNombre = universoNombre;
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

	public Integer getDepartamentoId() {
		return universoId;
	}

	public void setDepartamentoId(Integer universoId) {
		this.universoId = universoId;
	}

	public String getDepartamentoNombre() {
		return universoNombre;
	}

	public void setDepartamentoNombre(String universoNombre) {
		this.universoNombre = universoNombre;
	}

	public List<Integer> getPoderesId() {
		return poderesId;
	}

	public void setPoderesId(List<Integer> poderesId) {
		this.poderesId = poderesId;
	}

	public List<String> getPoderesNombre() {
		return poderesNombre;
	}

	public void setPoderesNombre(List<String> poderesNombre) {
		this.poderesNombre = poderesNombre;
	}
}
