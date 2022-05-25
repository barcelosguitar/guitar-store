package br.com.barcelos_projects.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.barcelos_projects.model.Request;
import br.com.barcelos_projects.repository.RequestDAO;

@Named ("requestBean")
@ApplicationScoped
public class RequestBean {

    @Inject
    private RequestDAO requestDAO;

    private Request request;

    public String addRequest(){
        this.requestDAO.add(request);
        return "";
    }
}