package br.com.unipar.gdsystem.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.unipar.gdsystem.model.Usuario;
import br.com.unipar.gdsystem.util.JPAUtil;

public class LoginDAO {
	
	private static EntityManager manager;

	public LoginDAO() {
		manager = new JPAUtil().getEntityManager();
	}

	public static Boolean validarLogin(String usuario, String senha) {
		manager = new JPAUtil().getEntityManager();

		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> root = query.from(Usuario.class);

		Path<String> usuarioPath = root.<String>get("usuario");
		Path<String> senhaPath = root.<String>get("senha");
		
		Predicate nomeIgual = criteriaBuilder.equal(usuarioPath, usuario);
		Predicate senhaIgual = criteriaBuilder.equal(senhaPath, senha);
		
		query.where(nomeIgual, senhaIgual);
		
		TypedQuery<Usuario> typedQuery = manager.createQuery(query);
		
		Usuario user = null;
		
		try {
			user = typedQuery.getSingleResult();
		} catch (Exception e) {
			return false;
		}
		
		if (user != null) {
			return true;
		}
		
		return false;
	}
	
}
