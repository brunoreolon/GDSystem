package br.com.unipar.gdsystem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

	public List<Produto> getList() {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Produto> query = criteriaBuilder.createQuery(Produto.class);
		Root<Produto> root = query.from(Produto.class);

		query.select(root);

		TypedQuery<Produto> typedQuery = manager.createQuery(query);

		return typedQuery.getResultList();
	}
	
	public void remove(Integer id) {
		Produto produto = search(id);
		
		manager.getTransaction().begin();
		manager.remove(produto);
		manager.getTransaction().commit();
	}
	
	public void update() {
		getList();
	}
	
	public Produto search(Integer id) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Produto> query = criteriaBuilder.createQuery(Produto.class);
		Root<Produto> root = query.from(Produto.class);
		
		Path<String> codigoInternoPath = root.<String>get("codigoInterno");
		
		Predicate codigoInternoIgual = criteriaBuilder.equal(codigoInternoPath, id);
		
		query.where(codigoInternoIgual);
		
		TypedQuery<Produto> typedQuery = manager.createQuery(query);
		
		return typedQuery.getSingleResult();
	}
}
