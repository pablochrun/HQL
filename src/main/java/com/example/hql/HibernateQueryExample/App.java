package com.example.hql.HibernateQueryExample;

import org.hibernate.HibernateException;

import com.example.hql.HibernateQueryExample.dao.AbstractDAO;
import com.example.hql.HibernateQueryExample.dao.UsuariosDAO;
import com.example.hql.HibernateQueryExample.modelo.Usuario;

/**
 * Hello world!
 *
 */
public class App {
	
	public App(){
		creaUsuarios();
		buscaUsuario();
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
}
