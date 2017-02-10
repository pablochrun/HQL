package com.example.hql.HibernateQueryExample;

import java.util.List;

import org.hibernate.HibernateException;

import com.example.hql.HibernateQueryExample.dao.AbstractDAO;
import com.example.hql.HibernateQueryExample.dao.UsuariosDAO;
import com.example.hql.HibernateQueryExample.modelo.Compra;
import com.example.hql.HibernateQueryExample.modelo.Direccion;
import com.example.hql.HibernateQueryExample.modelo.Producto;
import com.example.hql.HibernateQueryExample.modelo.Usuario;

/**
 * Hello world!
 *
 */
public class App {
	
	public App(){
		creaUsuarios();
		buscaUsuario();
		creaCompras();
		buscaUsuariosProductosInactivos();
	}
	
	public static void main(String[] args) {
		System.out.println("Hello World!");
		new App();
		System.out.println("Bye World!");
	}
	
	private void creaUsuarios() {
		try {
			AbstractDAO.almacenaEntidad(new Usuario("Usuario de Prueba numero 1", "estudioso", "desvelado"));
			AbstractDAO.almacenaEntidad(new Usuario("Usuario de Prueba numero 2", "caperucita", "loboFeroz"));
			AbstractDAO.almacenaEntidad(new Usuario("Usuario de Prueba numero 3", "empleado", "infelicidad"));
			AbstractDAO.almacenaEntidad(new Usuario("Usuario de Prueba numero 4", "usuarioComun", "password"));
			System.out.println("Usuarios creados");
		} catch (HibernateException he) {
			System.err.println("Ocurrió un error al agregar los usuarios...");
			he.printStackTrace();
		}
	}
	
	private void buscaUsuario() {
		Usuario usuario = null;
		UsuariosDAO usuariosDAO = new UsuariosDAO();

		try {
			usuario = usuariosDAO.getUsuario("caperucita", "loboFeroz");
		} catch (HibernateException e) {
			System.err.println("Ocurrió un error al recuperar usuario");
			e.printStackTrace();
		}

		if (usuario == null) {
			System.out.println("No se encontró al usuario");
		} else {
			System.out.println("El usuario es: " + usuario.getNombre());
		}
	}
	
	private void creaCompras() {
		Producto libros = new Producto("Libro", "ABC123456", 120.0F);
		Producto iPads = new Producto("iPad", "RAF755576", 1315.45F);
		Producto televisor = new Producto("T.V.", "AOF765984", 379.64F);
		Producto postales = new Producto("Postal", "ELF", 15.65F);
		Producto juegos = new Producto("Videojuego", "MEN", 158.24F);

		libros.setEstatus(Producto.Estatus.INACTIVO);
		postales.setEstatus(Producto.Estatus.INACTIVO);

		Usuario usuario1 = new Usuario("Usuario de Prueba numero 1", "estudioso", "desvelado");
		usuario1.setDireccion(new Direccion("calle principal", "12345"));

		Usuario usuario2 = new Usuario("Usuario de Prueba numero 2", "caperucita", "loboFeroz");
		usuario2.setDireccion(new Direccion("primera avenida", "AVR-175"));

		Usuario usuario3 = new Usuario("Usuario de Prueba numero 3", "empleado", "infelicidad");
		usuario3.setDireccion(new Direccion("puesta del sol", "12345"));

		Usuario usuario4 = new Usuario("Usuario de Prueba numero 4", "usuarioComun", "password");
		usuario4.setDireccion(new Direccion("Este 145", null));

		Compra compraUsuario1 = new Compra();
		compraUsuario1.addProducto(libros);
		usuario1.addCompra(compraUsuario1);

		compraUsuario1 = new Compra();
		compraUsuario1.addProducto(televisor);
		compraUsuario1.addProducto(juegos);
		usuario1.addCompra(compraUsuario1);

		Compra compraUsuario2 = new Compra();
		compraUsuario2.addProducto(iPads);
		compraUsuario2.addProducto(televisor);
		compraUsuario2.addProducto(juegos);
		usuario2.addCompra(compraUsuario2);

		Compra compraUsuario3 = new Compra();
		compraUsuario3.addProducto(iPads);
		compraUsuario3.addProducto(televisor);
		usuario3.addCompra(compraUsuario3);

		compraUsuario3 = new Compra();
		compraUsuario3.addProducto(postales);
		compraUsuario3.addProducto(juegos);
		usuario3.addCompra(compraUsuario3);

		Compra compraUsuario4 = new Compra();
		compraUsuario4.addProducto(libros);
		usuario4.addCompra(compraUsuario4);

		try {
			AbstractDAO.almacenaEntidad(libros);
			AbstractDAO.almacenaEntidad(iPads);
			AbstractDAO.almacenaEntidad(televisor);
			AbstractDAO.almacenaEntidad(postales);
			AbstractDAO.almacenaEntidad(juegos);

			AbstractDAO.almacenaEntidad(usuario1);
			AbstractDAO.almacenaEntidad(usuario2);
			AbstractDAO.almacenaEntidad(usuario3);
			AbstractDAO.almacenaEntidad(usuario4);
		} catch (HibernateException he) {
			System.err.println("Ocurrió un error al agregar los usuarios...");
			he.printStackTrace();
		}
	}
	

	private void buscaUsuariosProductosInactivos() {
		List<Usuario> listaUsuarios = new UsuariosDAO().getUsuariosConComprasInactivas("12345");

		for (int i = 0; i < listaUsuarios.size(); i++) {
			Usuario usuarioActual = listaUsuarios.get(i);

			System.out.println("\nUsuario: " + usuarioActual.getNombre());

			List<Compra> listaCompras = usuarioActual.getCompras();

			for (int numeroCompra = 0; numeroCompra < listaCompras.size(); numeroCompra++) {
				List<Producto> listaProductos = listaCompras.get(numeroCompra).getProductos();

				for (int numeroProducto = 0; numeroProducto < listaProductos.size(); numeroProducto++) {
					Producto producto = listaProductos.get(numeroProducto);

					System.out.println("\t" + producto.getNombre());
				}
			}
		}
	}
}
