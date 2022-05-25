package br.com.barcelos_projects.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.barcelos_projects.model.Guitar;
import br.com.barcelos_projects.repository.ShoppingCartDAO;

@Named ("cartBean")
@ApplicationScoped
public class CartBean implements Serializable{

    @Inject
    private ShoppingCartDAO cartDAO;

    private List<Guitar> selectedGuitars;
    private Guitar selectedGuitar;
    private Integer quantity;
    private Double subtotal;

    public String addToCart(Guitar guitar){
        try {
            this.selectedGuitar = new Guitar();
            this.selectedGuitar = guitar.clone();
            this.selectedGuitar.setQuantity(1);
            this.cartDAO.add(this.selectedGuitar);
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
            e.printStackTrace();
        }
    }
    public Double getSubtotal(){
        subtotal = 0.0d;
        for(Guitar g : this.cartDAO.listSelectedGuitars()){
            subtotal+=g.getPrice();
        }
        return subtotal;
    }
    public void updatedPrice(ValueChangeEvent event){

        this.selectedGuitar.setQuantity((Integer) event.getNewValue());
        this.quantity = (Integer) event.getNewValue();

        this.selectedGuitar.setPrice(quantity*selectedGuitar.getPrice());

        PrimeFaces.current().ajax().update("form:form-cart");
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
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public String requestData(){
        return "/request-data.xhtml";
    }
}