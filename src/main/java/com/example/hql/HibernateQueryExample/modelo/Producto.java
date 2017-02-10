package com.example.hql.HibernateQueryExample.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Producto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5490510858281776763L;

	public static enum Estatus {
		ACTIVO, INACTIVO
	};

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String codigoBarras;
	private float precio;
	private Estatus estatus = Estatus.ACTIVO;

	public Producto() {
	}

	public Producto(String nombre, String codigoBarras, float precio) {
		this.nombre = nombre;
		this.codigoBarras = codigoBarras;
		this.precio = precio;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public Estatus getEstatus() {
		return estatus;
	}

	public void setEstatus(Estatus estatus) {
		this.estatus = estatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
}