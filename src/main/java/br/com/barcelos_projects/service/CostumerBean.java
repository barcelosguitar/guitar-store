package br.com.barcelos_projects.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.barcelos_projects.enums.Payment;
import br.com.barcelos_projects.model.Address;
import br.com.barcelos_projects.model.Costumer;
import br.com.barcelos_projects.model.Guitar;
import br.com.barcelos_projects.model.Request;
import br.com.barcelos_projects.repository.AddressTemp;
import br.com.barcelos_projects.repository.CostumerDAO;
import br.com.barcelos_projects.repository.CostumerTemp;
import br.com.barcelos_projects.repository.RequestDAO;
import br.com.barcelos_projects.repository.ShoppingCartDAO;

@Named ("costumerBean")
@ApplicationScoped
public class CostumerBean {

    @Inject
    private CostumerDAO costumerDAO;
    @Inject
    private RequestDAO requestDAO;
    @Inject
    private CartBean cartBean;
    
    private Costumer costumer;
    private Address address;
    private Request request;
    private Payment payment;

    public String addCostumer(){
        if(costumer.getId()==null){
            this.costumerDAO.add(costumer);
        } else {

        }
        return "";
    }
    public String addAddress(){
        AddressTemp.address.add(address);
        this.costumer.setAddress(address);
        return"";
    }
    public String addRequest(){

        this.request.setCostumer(costumer);
        this.request.setPayment(payment);
        this.request.setValue(cartBean.getSubtotal());

        this.requestDAO.add(request);
        return "";
    }
    public Costumer getCostumer() {
        return costumer;
    }
    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
}