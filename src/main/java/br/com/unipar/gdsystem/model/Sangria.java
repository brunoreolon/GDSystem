package br.com.unipar.gdsystem.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sangria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Caixa caixa;

	private BigDecimal saldoAtualCaixa;
	private BigDecimal valorSangria;
	private BigDecimal valorPosSangria;
	private String motivo;

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getSaldoAtual() {
		return saldoAtualCaixa;
	}

	public void setSaldoAtual(BigDecimal saldoAtual) {
		this.saldoAtualCaixa = saldoAtual;
	}

	public BigDecimal getValorSangria() {
		return valorSangria;
	}

	public void setValorSangria(BigDecimal valorSangria) {
		this.valorSangria = valorSangria;
	}

	public BigDecimal getValorPosSangria() {
		return valorPosSangria;
	}

	public void setValorPosSangria(BigDecimal valorPosSangria) {
		this.valorPosSangria = valorPosSangria;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

}
