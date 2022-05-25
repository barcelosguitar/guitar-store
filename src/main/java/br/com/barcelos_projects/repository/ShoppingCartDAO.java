package br.com.barcelos_projects.repository;

import java.util.List;

import javax.ejb.Stateful;

import br.com.barcelos_projects.model.Guitar;

@Stateful
public class ShoppingCartDAO {

    public void add(Guitar guitar){
        try {
                ShoppingCartTemp.selectedGuitars.add(guitar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void remove(Guitar guitar){
        try {
            ShoppingCartTemp.selectedGuitars.remove(guitar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Guitar> listSelectedGuitars(){
        return ShoppingCartTemp.selectedGuitars;
    }
}