package br.com.unipar.gdsystem.enums;

public enum SituacaoEnum {
	ABERTO("Aberto"),
	FECHADO("Fechado");
	
	private SituacaoEnum(String descricao) {
		this.descricao = descricao;
	}
	
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
}
