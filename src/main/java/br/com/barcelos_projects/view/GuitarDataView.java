package br.com.barcelos_projects.view;

import java.io.Serializable;
/*import java.util.List;

import br.com.barcelos_projects.model.Guitar;
import br.com.barcelos_projects.repository.GuitarDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;


@ViewScoped
@Named ("guitarDataView")*/
public class GuitarDataView implements Serializable {

    /*private List<Guitar> guitars;

    @Inject
    private GuitarDAO guitarDAO;

    @PostConstruct
    public void init(){
        try {
            this.guitars = guitarDAO.listRandomGuitars();
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }

    public List<Guitar> getGuitars(){
        return this.guitarDAO.listGuitars();
    }
    public void clearMultiViewState() {
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        PrimeFaces.current().multiViewState().clearAll(viewId, true, this::showMessage);
    }

    private void showMessage(String clientId) {
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, clientId + " multiview state has been cleared out", null));
    }*/
}