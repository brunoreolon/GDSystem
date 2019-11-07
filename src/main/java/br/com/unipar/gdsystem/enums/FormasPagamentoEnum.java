package br.com.unipar.gdsystem.enums;

public enum FormasPagamentoEnum {
	DINHEIRO("Dinheiro"),
	CREDIARIO("Crediário"),
	CARTAO_DE_CREDITO("Cartão de Crédito"),
	CARTAO_DE_DEBITO("Cartão de Débito");
	
	private FormasPagamentoEnum(String descricao) {
		this.descricao = descricao;
	}
	
	private String descricao;
	
	@Override
	public String toString() {
		return descricao;
	}
}
