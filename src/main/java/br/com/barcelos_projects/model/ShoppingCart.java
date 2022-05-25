package br.com.barcelos_projects.model;

import java.util.List;

public class ShoppingCart {
    
    private String productName;
    private List<Guitar> selectedGuitars;
    private Double subtotal;
    private Double total;

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public List<Guitar> getSelectedGuitars() {
        return selectedGuitars;
    }
    public void setSelectedGuitars(List<Guitar> selectedGuitars) {
        this.selectedGuitars = selectedGuitars;
    }
    public Double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
}