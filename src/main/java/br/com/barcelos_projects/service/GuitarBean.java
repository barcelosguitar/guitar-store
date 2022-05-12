package br.com.barcelos_projects.service;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.barcelos_projects.enums.Brand;
import br.com.barcelos_projects.enums.Model;
import br.com.barcelos_projects.model.Guitar;
import br.com.barcelos_projects.repository.GuitarDAO;

@SessionScoped
@Named ("guitar")
public class GuitarBean implements Serializable{
	
	private String name;
	private String description;
	private String modelSelectedOption;
	private Model model;
	private Brand brand;
	private Double price;
	private String urlImg;

	@Inject
	private GuitarDAO guitarDAO;
	
	public void addGuitar() {
		//if(name!=null && description!=null && model!=null && brand!=null && price!=0.0) {
			Guitar newGuitar = new Guitar();
			newGuitar.setName(this.name);
			newGuitar.setDescription(this.description);
			newGuitar.setModel(this.model);
			newGuitar.setBrand(this.brand);
			newGuitar.setPrice(this.price);
			newGuitar.setUrlImg(this.urlImg);

			//if(newGuitar!=null)
				this.guitarDAO.add(newGuitar);
		//}
	}
	public String getModelSelectedOption() {
		return modelSelectedOption;
	}
	public void setModelSelectedOption(String model) {
		this.modelSelectedOption = model;
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