package com.example.demo.exception;

public class ResourceNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String mensaje) {
		super(mensaje);
	}
}