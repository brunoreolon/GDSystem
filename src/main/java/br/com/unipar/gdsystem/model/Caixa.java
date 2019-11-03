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

import br.com.unipar.gdsystem.enums.Situacao;

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
	private BigDecimal valorFechamento;
	private BigDecimal diferenca;
	
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	@OneToMany(mappedBy = "caixa")
	private List<Sangria> sangrias;
	
	public List<Sangria> getSangrias() {
		return sangrias;
	}

	public void setSangrias(List<Sangria> sangrias) {
		this.sangrias = sangrias;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
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
