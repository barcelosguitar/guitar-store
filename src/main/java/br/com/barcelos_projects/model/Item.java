package br.com.barcelos_projects.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name="item")
public class Item implements Serializable{
    
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne (fetch = FetchType.LAZY) 
    @JoinColumn (name="request_id")
    private Request request;
    @OneToOne 
    @JoinColumn(name = "guitar_id", unique = false, nullable = false, updatable = false)
    private Guitar guitar;
    @Column
    private Integer quantity;

    public Item(Request request, Guitar guitar, Integer quantity) {
        this.request = request;
        this.guitar = guitar;
        this.quantity = quantity;
    }
    public Item(Guitar guitar, Integer quantity) {
        this.guitar = guitar;
        this.quantity = quantity;
    }
    public Item() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Request getRequest() {
        return request;
    }
    public void setRequest(Request request) {
        this.request = request;
    }
    public Guitar getGuitar() {
        return guitar;
    }
    public void setGuitar(Guitar guitar) {
        this.guitar = guitar;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}