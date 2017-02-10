package com.example.hql.HibernateQueryExample.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Compra implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2866015977111257876L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToMany(fetch=FetchType.EAGER)
    private List<Producto> productos = new ArrayList<Producto>();
    
    private double importeTotal;
    
    @ManyToOne
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario)  {
        this.usuario = usuario;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void addProducto(Producto producto) {
        this.productos.add(producto);
        importeTotal += producto.getPrecio();
    }

    public long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }
}