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

	private Integer codigo;
	private String descricao;
	private String unidade;
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal descPorcentagem;
	private BigDecimal descDinheiro;
	private BigDecimal subTotal;

	public ProdutoVenda() {

	}
	
	public ProdutoVenda(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

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
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != ProdutoVenda.class) {
			return false;
		}
		
		ProdutoVenda other = (ProdutoVenda) obj;
		return this.codigo == other.codigo;
	}
	
	@Override
	public int hashCode() {
		return Integer.hashCode(codigo);
	}
}
