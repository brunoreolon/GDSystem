package br.com.unipar.gdsystem.dao;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.unipar.gdsystem.model.Caixa;
import br.com.unipar.gdsystem.util.JPAUtil;

public class CaixaDAO {
	
	private static EntityManager manager;
	Caixa caixa = new Caixa();
	
	public CaixaDAO() {
		manager = new JPAUtil().getEntityManager();
	}

	public void caixa(Caixa caixa) {
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
			System.out.println(false);
			return false;
		}else {
			System.out.println(true);
			return true;
		}
		
//		return  ((Caixa) caixa).getSituacao().equals("Aberto") ? true : false;
	}
	
	public Caixa getCaixa() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Caixa.class);
		criteria.addOrder(Order.desc("id"));
		criteria.setMaxResults(1);

		return (Caixa) criteria.uniqueResult();
		
	}

	public String getDataAbertura() {
		Caixa caixa =  getCaixa();
		
		return caixa.getDataAbertura();
	}

	public String getValorAbertura() {
		Caixa caixa =  getCaixa();
		return caixa.getValorAbertura().toString();
	}

}
