package br.com.unipar.gdsystem.inicio;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.unipar.gdsystem.enums.SituacaoEnum;
import br.com.unipar.gdsystem.model.Caixa;
import br.com.unipar.gdsystem.model.Sangria;
import br.com.unipar.gdsystem.util.JPAUtil;

public class Teste {

	public static void main(String[] args) {
		EntityManager manager = new JPAUtil().getEntityManager();
		
		Caixa caixa3 = new Caixa();
		Sangria sangria = new Sangria();
		Sangria sangria2 = new Sangria();
		
		caixa3.setSituacao(SituacaoEnum.FECHADO);
		caixa3.setValorAbertura(new BigDecimal("13"));
		
		sangria.setValorSangria(new BigDecimal("16"));
		sangria.setCaixa(caixa3);
		sangria2.setValorSangria(new BigDecimal("670"));
		sangria2.setCaixa(caixa3);
		
		manager.getTransaction().begin();
		
//		manager.persist(caixa3);
//		manager.persist(sangria);
//		manager.persist(sangria2);
		
		manager.getTransaction().commit();

		manager.close();
	}

}
