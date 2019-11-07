package br.com.unipar.gdsystem.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProdutoVenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Venda venda;

	private Integer quantidade;
	private BigDecimal subTotal;
	private BigDecimal descPorcentagem;
	private BigDecimal descDinheiro;

	public BigDecimal getDescPorcentagem() {
		return descPorcentagem;
	}

	public void setDescPorcentagem(BigDecimal descPorcentagem) {
		this.descPorcentagem = descPorcentagem;
	}

	public BigDecimal getDescDinheiro() {
		return descDinheiro;
	}

	public void setDescDinheiro(BigDecimal descDinheiro) {
		this.descDinheiro = descDinheiro;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

//	public Produto getProduto() {
//		return produto;
//	}
//
//	public void setProduto(Produto produto) {
//		this.produto = produto;
//	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Venda getVenda() {
		return venda;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
