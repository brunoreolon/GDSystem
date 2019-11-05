package br.com.unipar.gdsystem.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(mappedBy = "venda")
	private List<ProdutoVenda> produtos;

	private Calendar dataVenda;
//	private Cliente cliente;
	private BigDecimal valorTotal;
	private BigDecimal descontoDinheiro;
	private BigDecimal descontoPorcentagem;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ProdutoVenda> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoVenda> produtos) {
		this.produtos = produtos;
	}

	public Calendar getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Calendar dataVenda) {
		this.dataVenda = dataVenda;
	}

//	public Cliente getCliente() {
//		return cliente;
//	}
//
//	public void setCliente(Cliente cliente) {
//		this.cliente = cliente;
//	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getDescontoDinheiro() {
		return descontoDinheiro;
	}

	public void setDescontoDinheiro(BigDecimal descontoDinheiro) {
		this.descontoDinheiro = descontoDinheiro;
	}

	public BigDecimal getDescontoPorcentagem() {
		return descontoPorcentagem;
	}

	public void setDescontoPorcentagem(BigDecimal descontoPorcentagem) {
		this.descontoPorcentagem = descontoPorcentagem;
	}

}
