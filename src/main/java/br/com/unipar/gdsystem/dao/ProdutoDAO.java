package br.com.unipar.gdsystem.dao;

import javax.persistence.EntityManager;

import br.com.unipar.gdsystem.model.Produto;
import br.com.unipar.gdsystem.util.JPAUtil;

public class ProdutoDAO {
	private EntityManager manager;

	public ProdutoDAO() {
		manager = new JPAUtil().getEntityManager();
	}
	
	public void add(Produto produto) {
		manager.getTransaction().begin();
		manager.persist(produto);
		manager.getTransaction().commit();

		manager.close();
	}
}
