package br.com.unipar.gdsystem.dao;

import javax.persistence.EntityManager;

import br.com.unipar.gdsystem.model.Pedido;
import br.com.unipar.gdsystem.util.JPAUtil;

public class PedidoDAO {
	
	private EntityManager manager;
	
	public PedidoDAO() {
		manager = new JPAUtil().getEntityManager();
	}
	
	public void add(Pedido pedido) {
		manager.getTransaction().begin();
		manager.persist(pedido);
		manager.getTransaction().commit();

		manager.close();
	}

}
