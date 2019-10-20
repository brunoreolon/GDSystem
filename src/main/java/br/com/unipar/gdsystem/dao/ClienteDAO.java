package br.com.unipar.gdsystem.dao;

import javax.persistence.EntityManager;

import br.com.unipar.gdsystem.model.Cliente;
import br.com.unipar.gdsystem.util.JPAUtil;

public class ClienteDAO {

	private EntityManager manager;

	public ClienteDAO() {
		manager = new JPAUtil().getEntityManager();
	}
	
	public void add(Cliente cliente) {
		manager.getTransaction().begin();
		manager.persist(cliente);
		manager.getTransaction().commit();

		manager.close();
	}
}
