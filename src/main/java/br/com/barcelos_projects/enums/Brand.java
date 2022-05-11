package br.com.barcelos_projects.enums;

public enum Brand {
	FENDER("Fender"),
	SQUIER("Squier"),
	GIBSON("Gibson"),
	EPIPHONE("Epiphone"),
	IBANEZ("Ibanez"),
	GRETSCH("Gretsch");

	private String description;

	private Brand(String description) {
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