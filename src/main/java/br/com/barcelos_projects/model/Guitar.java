package br.com.barcelos_projects.model;

import java.io.Serializable;

import br.com.barcelos_projects.enums.Brand;
import br.com.barcelos_projects.enums.Model;


@SuppressWarnings("serial")
//@Entity
public class Guitar implements Serializable{
	
	private String name;
	private String description;
	//@Enumerated
	private Model model;
	//@Enumerated
	private Brand brand;
	private Double price;
	private String urlImg;
	
	public Guitar(String name, String description, Model model, Brand brand, double price, String urlImg) {
		this.name = name;
		this.description = description;
		this.model = model;
		this.brand = brand;
		this.price = price;
		this.urlImg = urlImg;
	}
	public Guitar() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getUrlImg() {
		return urlImg;
	}
	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}
}