package com.example.hql.HibernateQueryExample.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.example.hql.HibernateQueryExample.modelo.Producto;
import com.example.hql.HibernateQueryExample.modelo.Usuario;

public class UsuariosDAO extends AbstractDAO {

	public Usuario getUsuario(String username, String password) throws HibernateException {
		Usuario usuario = null;

		try {
			iniciaOperacion();
			Query<?> query = getSession()
					.createQuery("FROM Ususario u WHERE u. username = :nombreUsuario AND u. password = :password");
			query.setParameter("nombreUsuario", username);
			query.setParameter("password", password);
			usuario = (Usuario) query.getSingleResult();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			terminaOperacion();
		}

		return usuario;
	}
	
	public List<Usuario> getUsuariosConComprasInactivas(String codigoPostal) throws HibernateException {
		List<Usuario> listaUsuarios = null;

		try {
			iniciaOperacion();

			Query<?> query = getSession().createQuery(
					"FROM Usuario u JOIN u.compras c JOIN c.productos p WHERE p.estatus = ? AND u.direccion.codigoPostal = ?");
			query.setParameter(0, Producto.Estatus.INACTIVO);
			query.setParameter(1, codigoPostal);

			listaUsuarios = castList(Usuario.class, query.getResultList());

		} catch (HibernateException he) {
			he.printStackTrace();
			manejaExcepcion(he);
		} finally {
			terminaOperacion();
		}

		return listaUsuarios;
	}

}
