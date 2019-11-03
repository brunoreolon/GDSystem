package br.com.unipar.gdsystem.dao;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.unipar.gdsystem.enums.Situacao;
import br.com.unipar.gdsystem.model.Caixa;
import br.com.unipar.gdsystem.util.AlertUTIL;
import br.com.unipar.gdsystem.util.JPAUtil;

public class CaixaDAO {

	private static EntityManager manager;
	private Caixa caixa;
	public static CaixaDAO caixaDAO;

	public CaixaDAO() {
		caixa  = new Caixa();
		caixaDAO = this;
		manager = new JPAUtil().getEntityManager();
	}

	public void add(Caixa caixa) {
		manager.getTransaction().begin();
		manager.persist(caixa);
		manager.getTransaction().commit();

		manager.close();
	}

	public void fecharCaixa() {

	}

	public boolean isOpen() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Caixa.class);
		criteria.addOrder(Order.desc("id"));
		criteria.setMaxResults(1);

		caixa = (Caixa) criteria.uniqueResult();

		if (caixa == null) {
			return false;
		} else {
			return true;
		}

//		return  ((Caixa) caixa).getSituacao().equals("Aberto") ? true : false;
	}

	public static Caixa getCaixa() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Caixa.class);
		criteria.addOrder(Order.desc("id"));
		criteria.setMaxResults(1);

		return (Caixa) criteria.uniqueResult();

	}
	public static Caixa getCaixaAberto() {
		manager = new JPAUtil().getEntityManager();
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Caixa> query = criteriaBuilder.createQuery(Caixa.class);
		Root<Caixa> root = query.from(Caixa.class);

		Path<String> situacaoPath = root.<String>get("situacao");
		
		Predicate situacaoIgual = criteriaBuilder.equal(situacaoPath, Situacao.ABERTO);
		
		query.where(situacaoIgual);
		
		TypedQuery<Caixa> typedQuery = manager.createQuery(query);
		
		try {
			Caixa c = typedQuery.getSingleResult();
		} catch (Exception e) {
			AlertUTIL.alertError("", "Abra o caixa para realizar uma sangria");
		}
		
		return typedQuery.getSingleResult();
	}

	public static Calendar getDataAbertura() {
		Caixa caixa = getCaixa();

		return caixa.getDataAbertura();
	}

	public static Calendar getDataFechamento() {
		Caixa caixa = getCaixa();
		
		return caixa.getDataFechamento();
	}

	public String getValorAbertura() {
		Caixa caixa = getCaixa();
		return caixa.getValorAbertura().toString();
	}

	public void update(Caixa caixa) {
		Caixa cx = getCaixa();

		cx.setValorFechamento(caixa.getValorFechamento());
		cx.setDataFechamento(caixa.getDataFechamento());
		cx.setSituacao(caixa.getSituacao());
		cx.setDiferenca(caixa.getDiferenca());

		add(cx);
	}
}
