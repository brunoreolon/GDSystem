package br.com.unipar.gdsystem.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.unipar.gdsystem.enums.SituacaoEnum;

@Entity
public class Caixa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataAbertura;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataFechamento;

	private BigDecimal valorAbertura;
	private BigDecimal valorAtualCaixa;
	private BigDecimal valorFechamento;
	private BigDecimal diferenca;

	@Enumerated(EnumType.STRING)
	private SituacaoEnum situacaoEnum;

	@OneToMany(mappedBy = "caixa")
	private List<Sangria> sangrias;

	public BigDecimal getValorAtual() {
		return valorAtualCaixa;
	}

	public void setValorAtual(BigDecimal valorAtual) {
		this.valorAtualCaixa = valorAtual;
	}

	public List<Sangria> getSangrias() {
		return sangrias;
	}

	public void setSangrias(List<Sangria> sangrias) {
		this.sangrias = sangrias;
	}

	public SituacaoEnum getSituacao() {
		return situacaoEnum;
	}

	public void setSituacao(SituacaoEnum situacaoEnum) {
		this.situacaoEnum = situacaoEnum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Calendar dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Calendar getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Calendar dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public BigDecimal getValorAbertura() {
		return valorAbertura;
	}

	public void setValorAbertura(BigDecimal valorAbertura) {
		this.valorAbertura = valorAbertura;
	}

	public BigDecimal getValorFechamento() {
		return valorFechamento;
	}

	public void setValorFechamento(BigDecimal valorFechamento) {
		this.valorFechamento = valorFechamento;
	}

	public BigDecimal getDiferenca() {
		return diferenca;
	}

	public void setDiferenca(BigDecimal diferenca) {
		this.diferenca = diferenca;
	}

}
