package br.com.unipar.gdsystem.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.unipar.gdsystem.controller.VendaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Crediario {
	
	private Integer numeroParcelas = 0;
	private BigDecimal valorCompra = VendaController.vendaController.getValorTotal();
	private BigDecimal valorParcela = new BigDecimal("0");
	private List<Crediario> crediario = new ArrayList<Crediario>();

	public Crediario(Integer parcela) {
		this.numeroParcelas = parcela;
	}
	
	public Crediario(Integer parcela, BigDecimal valorParcela) {
		this.numeroParcelas = parcela;
		this.valorParcela = valorParcela;
	}

	private BigDecimal calcularValorParcela() {
		valorParcela = valorCompra.divide(new BigDecimal(numeroParcelas), BigDecimal.ROUND_UP);
		return valorParcela;
	}
	
	public List<Crediario> listCrediario() {
		BigDecimal valor = null;
		for (int i = 1; i <= numeroParcelas; i++) {
			valor = calcularValorParcela();
			crediario.add(new Crediario(i, valor));
		}
		
		return crediario;
	}
	
	public ObservableList<Crediario> observableListCrediario() {
		return FXCollections.observableArrayList(listCrediario());
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}
}
