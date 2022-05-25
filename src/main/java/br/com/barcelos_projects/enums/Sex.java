package br.com.barcelos_projects.enums;

public enum Sex {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    OUTROS("Outros");

    private String description;

    private Sex(String description) {
		this.description = description;
	}
	public String getDescription(){
		return this.description;
	}
	@Override
	public String toString(){
		return this.description;
	}
}