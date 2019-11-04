package br.com.unipar.gdsystem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

//		manager.close();
	}
	
	public List<Cliente> getList(){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Cliente> query = criteriaBuilder.createQuery(Cliente.class);
		Root<Cliente> root = query.from(Cliente.class);
		
		query.select(root);
		
		TypedQuery<Cliente> typedQuery = manager.createQuery(query);
		
		return typedQuery.getResultList();
	}
	
	public void remove(Integer id) {
		Cliente cliente = search(id);
		
		manager.getTransaction().begin();
		manager.remove(cliente);
		manager.getTransaction().commit();
	}
	
	public void update() {
		getList();
	}
	
	public Cliente search(Integer id) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Cliente> query = criteriaBuilder.createQuery(Cliente.class);
		Root<Cliente> root = query.from(Cliente.class);
		
		Path<String> idPath = root.<String>get("id");
		
		Predicate nomeIgual = criteriaBuilder.equal(idPath, id);
		
		query.where(nomeIgual);
		
		TypedQuery<Cliente> typedQuery = manager.createQuery(query);
		
		return typedQuery.getSingleResult();
	}
	
	public List<Cliente> searchNome(String nome) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
	    CriteriaQuery<Cliente> query = criteriaBuilder.createQuery(Cliente.class);
	    Root<Cliente> root = query.from(Cliente.class);

	    Path<String> nomePath = root.<String> get("nome");
		
	    Predicate nomeIgual = criteriaBuilder.like(nomePath, "%" + nome + "%");
	    
	    query.where(nomeIgual);

	    TypedQuery<Cliente> typedQuery = manager.createQuery(query);
	    
		return typedQuery.getResultList();
	}
	
	public Cliente searchCpf(String cpf) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Cliente> query = criteriaBuilder.createQuery(Cliente.class);
		Root<Cliente> root = query.from(Cliente.class);
		
		Path<String> cpfPath = root.<String>get("cpf");
		
		Predicate cpfIgual = criteriaBuilder.equal(cpfPath, cpf);
		
		query.where(cpfIgual);
		
		TypedQuery<Cliente> typedQuery = manager.createQuery(query);
		
		return typedQuery.getSingleResult();
	}
	
	public Cliente searchRg(String rg) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Cliente> query = criteriaBuilder.createQuery(Cliente.class);
		Root<Cliente> root = query.from(Cliente.class);
		
		Path<String> rgPath = root.<String>get("cpf");
		
		Predicate rgIgual = criteriaBuilder.equal(rgPath, rg);
		
		query.where(rgIgual);
		
		TypedQuery<Cliente> typedQuery = manager.createQuery(query);
		
		return typedQuery.getSingleResult();
	}
}
