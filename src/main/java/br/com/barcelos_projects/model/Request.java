package br.com.barcelos_projects.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.barcelos_projects.enums.Payment;

@Entity
@Table (name="request")
public class Request implements Serializable{

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="costumer_id")
    private Costumer costumer;
    @Column
    private Double value;
    @Enumerated (EnumType.STRING)
    private Payment payment;
    @OneToMany (mappedBy = "request", targetEntity = Item.class, cascade = CascadeType.ALL)
    private List<Item> items;

    public Request(Long id, Costumer costumer, Double value, Payment payment) {
        this.id = id;
        this.costumer = costumer;
        this.value = value;
        this.payment = payment;
    }
    public Request(){}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Costumer getCostumer() {
        return costumer;
    }
    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }
    public Double getValue() {
        return value;
    }
    public void setValue(Double value) {
        this.value = value;
    }
    public Payment getPayment() {
        return payment;
    }
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}