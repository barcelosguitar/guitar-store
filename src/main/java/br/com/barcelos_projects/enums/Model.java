package br.com.barcelos_projects.enums;

public enum Model {
	STRATOCASTER("Stratocaster"),
	TELECASTER("Telecaster"),
	LES_PAUL("Les Paul"),
	SG("SG"),
	SEMI_ACOUSTIC("Semi-acústica");

	private String description;

	private Model(String description) {
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