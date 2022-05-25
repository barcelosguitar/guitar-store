package br.com.barcelos_projects.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.barcelos_projects.model.Guitar;
import br.com.barcelos_projects.repository.GuitarDAO;
import br.com.barcelos_projects.repository.ShoppingCartDAO;

@Named ("cartBean")
@ApplicationScoped
public class CartBean implements Serializable{

    @Inject
    private ShoppingCartDAO cartDAO;

    private GuitarDAO guitarDAO;
    //private List<Guitar> guitars;
    private List<Guitar> selectedGuitars;
    private Guitar selectedGuitar;

    private String name;
    private Double price;
    private Integer quantity;
    private Double subtotal;

    public String addToCart(Guitar guitar){
        try {
            //selectedGuitar = new Guitar();
            //if(selectedGuitar.getCode()!=guitar.getCode()){
                this.selectedGuitar = new Guitar();
                this.selectedGuitar.setCode(guitar.getCode());
                this.selectedGuitar.setImg(guitar.getImg());
                this.selectedGuitar.setName(guitar.getName());
                this.selectedGuitar.setPrice(guitar.getPrice());

                this.setQuantity(1);
                this.cartDAO.add(this.selectedGuitar);

                
            //}
        } catch (Exception e) {
            e.getMessage();
        }
        return "";
    }
    public void remove(Guitar guitar){
        try {
            if(selectedGuitar.getId()!=null){
                this.setQuantity(0);
                this.cartDAO.remove(guitar);
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    public Double getSubtotal(){
        double result;
        for(Guitar g : this.cartDAO.listSelectedGuitars()){
            subtotal += selectedGuitar.getPrice();
        }
        return subtotal;
    }
    public void setSubtotal(Double subtotal){
        this.subtotal = subtotal;
    }

    public List<Guitar> getSelectedGuitars() {
        selectedGuitars = this.cartDAO.listSelectedGuitars();
        return selectedGuitars;
    }
    public void setSelectedGuitars(List<Guitar> selectedGuitars) {
        this.selectedGuitars = selectedGuitars;
    }
    public Guitar getSelectedGuitar() {
        return selectedGuitar;
    }
    public void setSelectedGuitar(Guitar selectedGuitar) {
        this.selectedGuitar = selectedGuitar;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getQuantity() {
        this.selectedGuitar.setPrice(quantity*this.selectedGuitar.getPrice());
        PrimeFaces.current().ajax().update("form:messages", "form:form-cart");
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.selectedGuitar.setPrice(quantity*selectedGuitar.getPrice());
        this.quantity = quantity;
        PrimeFaces.current().ajax().update("form:messages", "form:form-cart");
    }
}