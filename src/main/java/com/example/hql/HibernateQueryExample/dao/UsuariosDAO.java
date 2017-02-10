package com.example.hql.HibernateQueryExample.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

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

}
