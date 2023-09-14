package com.alura.jdbc.modelo;

public class Categoria {

	private Integer id;
	private String nombre;

	public Categoria(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
}
