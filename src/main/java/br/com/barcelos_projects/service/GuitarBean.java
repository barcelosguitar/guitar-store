package br.com.barcelos_projects.service;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.barcelos_projects.enums.Brand;
import br.com.barcelos_projects.enums.Model;
import br.com.barcelos_projects.model.Guitar;
import br.com.barcelos_projects.repository.GuitarDAO;

@SuppressWarnings("serial")
@SessionScoped
@Named ("guitar")
public class GuitarBean implements Serializable{
	
	@Inject
	private GuitarDAO guitarDAO;
	
	private String name;
	private String description;
	private Model model;
	private Brand brand;
	private Double price;
	private String urlImg;
	
	public void addGuitar() {
		if(name!=null && description!=null && model!=null && brand!=null && price!=0.0 && urlImg!=null) {
			Guitar newGuitar = new Guitar();
			newGuitar.setName(this.name);
			newGuitar.setDescription(this.description);
			newGuitar.setModel(this.model);
			newGuitar.setBrand(this.brand);
			newGuitar.setPrice(this.price);
			newGuitar.setUrlImg(this.urlImg);
			
			if(newGuitar!=null)
				this.guitarDAO.add(newGuitar);
		}
	}
}