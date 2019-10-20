package br.com.unipar.gdsystem.dao;

import javax.persistence.EntityManager;

import br.com.unipar.gdsystem.model.Usuario;
import br.com.unipar.gdsystem.util.JPAUtil;

public class UsuarioDAO {
	
	private EntityManager manager;

	public UsuarioDAO() {
		manager = new JPAUtil().getEntityManager();
	}
	
	public void add(Usuario usuario) {
		manager.getTransaction().begin();
		manager.persist(usuario);
		manager.getTransaction().commit();

		manager.close();
	}
}
