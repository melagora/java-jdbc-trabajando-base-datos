package com.alura.jdbc.modelo;

public class Producto {
	
	//Esta clase representa la tabla productos
	
	private Integer id;
	private String nombre;
	private String descripcion;
	private Integer cantidad;
	
	public Producto(String nombre, String descripcion, Integer cantidad) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return String.format(
				"{id: %s, nombre: %s, descripcion: %s, cantidad: %d}",
				this.id,
				this.nombre,
				this.descripcion,
				this.cantidad);
	}
	
}
