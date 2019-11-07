package br.com.unipar.gdsystem.enums;

public enum FormasPagamentoEnum {
	DINHEIRO("Dinheiro"),
	CREDIARIO("Credi�rio"),
	CARTAO_DE_CREDITO("Cart�o de Cr�dito"),
	CARTAO_DE_DEBITO("Cart�o de D�bito");
	
	private FormasPagamentoEnum(String descricao) {
		this.descricao = descricao;
	}
	
	private String descricao;
	
	@Override
	public String toString() {
		return descricao;
	}
}
