package br.com.barcelos_projects.service;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import br.com.barcelos_projects.enums.Brand;
import br.com.barcelos_projects.enums.Model;
import br.com.barcelos_projects.model.Guitar;
import br.com.barcelos_projects.repository.GuitarDAO;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SessionScoped
@Named ("guitarBean")
public class GuitarBean implements Serializable{

        private String name;
        private String description;
        private Model model;
        private Brand brand;
        private Double price;
        private StreamedContent img;
        
        @Inject
        private GuitarDAO guitarDAO;
        //private Path linuxPath = Paths.get("/home/barcelos/Pictures/GuitarStore/guitars");
        private Path winPath = Paths.get("src\\main\\resources\\tmp");

        /*@PostConstruct
        public void init(){
                try {
                        this.guitars = guitarDAO.listRandomGuitars();
                } catch (Exception e) {
                        e.printStackTrace();
                }    
        }*/
        public void addGuitar() throws IOException {

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
                                newGuitar.setImg(Files.readAllBytes(f.toPath()));
                        }
                }           

                this.guitarDAO.add(newGuitar);
                
                for(File f : list){
                        if(f.setLastModified(System.currentTimeMillis())){
                                f.delete();
                        }
                }  
        }
        
        public List<Guitar> getGuitars(){
                return this.guitarDAO.listGuitars();
        }
        public List<Guitar> getRandomGuitarList(){
                return this.guitarDAO.listRandomGuitars();
        }
        public StreamedContent getFoto(Guitar guitar){

                Guitar findGuitar = guitarDAO.findById(guitar);

                String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("image_id");

                try{
                    BufferedInputStream in = new BufferedInputStream(new FileInputStream(img));
                    in.read(findGuitar.getImg());
                    in.close();
                    content = new DefaultStreamedContent(new ByteArrayInputStream(findGuitar.getImg()), " ");
                }catch(Exception e){
                    e.printStackTrace();
                }
                return content;
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
        public String getImg () {
                return img;
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