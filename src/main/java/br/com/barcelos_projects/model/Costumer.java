package br.com.barcelos_projects.model;

import java.io.Serializable;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.barcelos_projects.enums.Sex;

@Entity
@Table (name="costumer")
public class Costumer implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String name;
    @Column
    private String cpf;
    @Column
    private String rg;
    @Enumerated (EnumType.STRING)
    private Sex sex;
    @Column
    private String birthday;
    @Column
    private String cell;
    @Column
    private String phone;
    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name="address_id")
    private Address address;

    public Costumer(String email, String name, 
            String cpf, String rg, Sex sex, String 
            birthday, String cell, String phone){
        this.email = email;
        this.name = name;
        this.cpf = cpf;
        this.rg = rg;
        this.sex = sex;
        this.birthday = birthday;
        this.cell = cell;
        this.phone = phone;
    }
    public Costumer(){}

    @Override
    public Costumer clone(){
        return new Costumer(getEmail(), getName(), getCpf(), 
            getRg(), getSex(), getBirthday(), getCell(), getPhone());
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getRg() {
        return rg;
    }
    public void setRg(String rg) {
        this.rg = rg;
    }
    public Sex getSex() {
        return sex;
    }
    public void setSex(Sex sex) {
        this.sex = sex;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getCell() {
        return cell;
    }
    public void setCell(String cell) {
        this.cell = cell;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }   
}
