package br.com.unipar.gdsystem.dao;

import javax.persistence.EntityManager;

import br.com.unipar.gdsystem.model.Venda;
import br.com.unipar.gdsystem.util.JPAUtil;

public class VendaDAO {
	
	private EntityManager manager;
	
	public VendaDAO() {
		
	}
	
	public void add(Venda venda) {
		manager = new JPAUtil().getEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(venda);
		manager.getTransaction().commit();

		manager.close();
	}
}
