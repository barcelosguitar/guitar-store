package br.com.barcelos_projects.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
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
	private String code;
	@Column
	private String description;
	@Column
	@Enumerated (EnumType.STRING)
	private Model model;
	@Column
	@Enumerated (EnumType.STRING)
	private Brand brand;
	@Column
	private Double price;
	@Column
	private String img;
	@Column
	private Integer quantity;

	public Guitar(String code, String name, String description, Model model, Brand brand, double price, String img, Integer quantity) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.model = model;
		this.brand = brand;
		this.price = price;
		this.img = img;
		this.quantity = quantity;
	}
	public Guitar() {}
	
	@Override
    public Guitar clone() {
        return new Guitar(getCode(), getName(), getDescription(), getModel(), getBrand(), getPrice(), getImg(), getQuantity());
    }

	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public String getCode(){
		return code;
	}
	public void setCode(String code){
		this.code = code;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getQuantity(){
		return quantity;
	}
	public void setQuantity(Integer quantity){
		this.quantity = quantity;
	}
	public static String upperCaseFirst(String value) {
		char[] arr = value.toCharArray();
		arr[0] = Character.toUpperCase(arr[0]);
		return new String(arr);
	}
}