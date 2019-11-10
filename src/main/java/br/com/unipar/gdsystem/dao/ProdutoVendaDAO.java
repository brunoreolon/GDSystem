package br.com.unipar.gdsystem.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.unipar.gdsystem.model.ProdutoVenda;
import br.com.unipar.gdsystem.util.JPAUtil;

public class ProdutoVendaDAO {
	private EntityManager manager;

	public ProdutoVendaDAO() {
		
	}

	public void add(List<ProdutoVenda> produtos){
		manager = new JPAUtil().getEntityManager();
		
		manager.getTransaction().begin();
		
		for (ProdutoVenda produtoVenda : produtos) {
			manager.persist(produtoVenda);
		}
		
		manager.getTransaction().commit();

		manager.close();
	}
}
