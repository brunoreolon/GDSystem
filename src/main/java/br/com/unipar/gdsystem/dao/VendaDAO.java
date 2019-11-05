package br.com.unipar.gdsystem.dao;

import javax.persistence.EntityManager;

import br.com.unipar.gdsystem.model.Venda;
import br.com.unipar.gdsystem.util.JPAUtil;

public class VendaDAO {
	
	private EntityManager manager;
	
	public VendaDAO() {
		manager = new JPAUtil().getEntityManager();
	}
	
	public void add(Venda venda) {
		manager.getTransaction().begin();
		manager.persist(venda);
		manager.getTransaction().commit();

		manager.close();
	}

}
