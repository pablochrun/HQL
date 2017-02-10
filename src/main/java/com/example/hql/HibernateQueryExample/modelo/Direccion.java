package com.example.hql.HibernateQueryExample.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Direccion implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3355534121453609069L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String calle;
    private String codigoPostal;

    public Direccion() {
    }

    public Direccion(String calle, String codigoPostal) {
        this.calle = calle;
        this.codigoPostal = codigoPostal;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }
}