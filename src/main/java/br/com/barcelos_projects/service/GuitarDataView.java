package br.com.barcelos_projects.service;

import java.io.Serializable;
import java.util.List;

import br.com.barcelos_projects.model.Guitar;
import br.com.barcelos_projects.repository.GuitarDAO;
import jakarta.enterprise.context.RequestScoped;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.StreamedContent;

@RequestScoped
@Named ("guitarDataView")
public class GuitarDataView implements Serializable {

    @Inject
    private GuitarDAO guitarDAO;
    private List<Guitar> guitars;

    @PostConstruct
    public void init(){
            try {
                    this.guitars = guitarDAO.listGuitars();    
            } catch (Exception e) {
                    e.printStackTrace();
            }
            
    }

    public List<Guitar> getGuitars(){
        guitars = this.guitarDAO.listGuitars();
        return guitars;
    }
}