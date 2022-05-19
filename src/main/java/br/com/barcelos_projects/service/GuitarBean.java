package br.com.barcelos_projects.service;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.barcelos_projects.enums.Brand;
import br.com.barcelos_projects.enums.Model;
import br.com.barcelos_projects.model.Guitar;
import br.com.barcelos_projects.repository.GuitarDAO;

@SessionScoped
@Named ("guitarBean")
public class GuitarBean implements Serializable{

        private String name;
        private String description;
        private Model model;
        private Brand brand;
        private Double price;
        private String img;
        
        @Inject
        private GuitarDAO guitarDAO;
        
        //private Path linuxPath = Paths.get("/home/barcelos/git/guitar-store/src/main/webapp/resourcers/img/");
        private Path winPath = Paths.get("C:\\Users\\Usuário\\git\\guitar-store\\src\\main\\webapp\\resources\\img");
        private String absolutePath;

        public void addGuitar() {
                try {
                        Guitar newGuitar = new Guitar();
                        newGuitar.setName(this.name);
                        newGuitar.setDescription(this.description);
                        newGuitar.setModel(this.model);
                        newGuitar.setBrand(this.brand);
                        newGuitar.setPrice(this.price);

                        File newFile = new File(winPath.toString());
                        File[] list = newFile.listFiles();

                        for(File f : list){
                                if(f.setLastModified(System.currentTimeMillis())){
                                        absolutePath = f.getName();
                                        newGuitar.setImg(absolutePath);
                                }
                        }           

                        this.guitarDAO.add(newGuitar);
                
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        
        public List<Guitar> getGuitars(){
                return this.guitarDAO.listGuitars();
        }
        public List<Guitar> getRandomGuitarList(){
                return this.guitarDAO.listRandomGuitars();
        }

        public String getName() {
                return name;
        }
        public void setName(String name) {
                this.name = name;
        }
        public String getDescription() {
                return description;
        }
        public void setDescription(String description) {
                this.description = description;
        }
        public Model getModel() {
                return model;
        }
        public void setModel(Model model) {
                this.model = model;
        }
        public Brand getBrand() {
                return brand;
        }
        public void setBrand(Brand brand) {
                this.brand = brand;
        }
        public Double getPrice() {
                return price;
        }
        public void setPrice(Double price) {
                this.price = price;
        }
        public String getImg(){
                File img = new File(winPath.toString());
                File[] fileList = img.listFiles();

                for(Guitar g : guitarDAO.listGuitars()){
                        for(File f : fileList){
                                if(g.getImg().equals(f.getName())){
                                        this.img = g.getImg();
                                        return this.img;
                                }
                        }
                }
                return null;
        }
        public void setImg(String img) {
                this.img = img;
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
        }
}