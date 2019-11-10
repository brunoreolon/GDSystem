package br.com.unipar.gdsystem.dao;

import javax.persistence.EntityManager;

import br.com.unipar.gdsystem.model.Sangria;
import br.com.unipar.gdsystem.util.JPAUtil;

public class SangriaDAO {

	private static EntityManager manager;

	public SangriaDAO() {
		
	}

	public void add(Sangria sangria) {
		manager = new JPAUtil().getEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(sangria);
		manager.getTransaction().commit();

		manager.close();
	}
}
