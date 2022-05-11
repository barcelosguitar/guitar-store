package br.com.barcelos_projects.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.barcelos_projects.enums.Brand;
import br.com.barcelos_projects.enums.Model;


@Entity
@Table (name="guitar")
public class Guitar implements Serializable{
	
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private String description;
	@Enumerated
	private Model model;
	@Enumerated
	private Brand brand;
	@Column
	private Double price;
	@Column (name="url_img")
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
	
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
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