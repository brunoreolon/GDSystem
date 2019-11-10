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
		manager = new JPAUtil().getEntityManager();
		
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
		manager = new JPAUtil().getEntityManager();
		Produto produto = search(id);
		
		manager.getTransaction().begin();
		manager.remove(produto);
		manager.getTransaction().commit();
		
		manager.close();
	}
	
	public void update(Produto produto) {
		manager = new JPAUtil().getEntityManager();
		
		manager.getTransaction().begin();
		manager.merge(produto);
		manager.getTransaction().commit();
		
		manager.close();
	}
	
	public Produto search(Integer codigo) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Produto> query = criteriaBuilder.createQuery(Produto.class);
		Root<Produto> root = query.from(Produto.class);
		
		Path<String> codigoPath = root.<String>get("codigo");
		
		Predicate codigoIgual = criteriaBuilder.equal(codigoPath, codigo);
		
		query.where(codigoIgual);
		
		TypedQuery<Produto> typedQuery = manager.createQuery(query);
		
		return typedQuery.getSingleResult();
	}
}
