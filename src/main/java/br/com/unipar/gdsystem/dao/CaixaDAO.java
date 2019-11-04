package br.com.unipar.gdsystem.dao;

import java.util.Calendar;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.unipar.gdsystem.enums.SituacaoEnum;
import br.com.unipar.gdsystem.model.Caixa;
import br.com.unipar.gdsystem.util.JPAUtil;

public class CaixaDAO {

	private static EntityManager manager;
	public static CaixaDAO caixaDAO;

	public CaixaDAO() {
		caixaDAO = this;
		manager = new JPAUtil().getEntityManager();
	}

	public static void add(Caixa caixa) {
		manager.getTransaction().begin();
		manager.persist(caixa);
		manager.getTransaction().commit();

	}

	public static void update(Caixa cx) {
		manager.getTransaction().begin();
		manager.merge(cx);
		manager.getTransaction().commit();

//		manager.close();
	}
	
	public Boolean isOpen() {
		Caixa caixa = getCaixa();
		
		if (caixa == null) {
			return false;
		} 
		
		return  caixa.getSituacao().equals(SituacaoEnum.ABERTO) ? true : false;
	}

	public Caixa getCaixa() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Caixa.class);
		criteria.addOrder(Order.desc("id"));
		criteria.setMaxResults(1);

		return (Caixa) criteria.uniqueResult();
	}
	
	public Calendar getDataAbertura() {
		return getCaixa().getDataAbertura();
	}

	public Calendar getDataFechamento() {
		return getCaixa().getDataFechamento();
	}

	public String getValorAbertura() {
		return String.valueOf(getCaixa().getValorAbertura());
	}
}
