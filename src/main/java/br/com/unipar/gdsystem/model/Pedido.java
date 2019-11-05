package br.com.unipar.gdsystem.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String numeroPedido;
	
	private Calendar data;
	private Calendar hora;
	private String cpf;
	private String cliente;

//	@OneToMany(mappedBy = "pedido")
//	private List<Produto> produtos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPedido() {
		return numeroPedido;
	}

	public void setPedido(String pedido) {
		this.numeroPedido = pedido;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Calendar getHora() {
		return hora;
	}

	public void setHora(Calendar hora) {
		this.hora = hora;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

//	public List<Produto> getProdutos() {
//		return produtos;
//	}
//
//	public void setProdutos(List<Produto> produtos) {
//		this.produtos = produtos;
//	}
}
