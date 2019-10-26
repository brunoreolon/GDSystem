package br.com.unipar.gdsystem.enums;

public enum SexoEnum {
	MASCULINO("Masculino"),
	FEMININO("Feminino");
	
	private SexoEnum(String descricao) {
		this.descricao = descricao;
	}
	
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
}
