package br.com.barcelos_projects.service;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import br.com.barcelos_projects.enums.Brand;
import br.com.barcelos_projects.enums.Model;
import br.com.barcelos_projects.model.Guitar;
import br.com.barcelos_projects.repository.GuitarDAO;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;

import com.rometools.rome.feed.rss.Image;

import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
        private Path linuxPath = Paths.get("/home/barcelos/git/guitar-store/src/main/webapp/resourcers/img/");
        private String absolutePath;
        //private Path winPath = Paths.get("src\\main\\resources\\tmp");

        public void addGuitar() {
                try {
                        Guitar newGuitar = new Guitar();
                        newGuitar.setName(this.name);
                        newGuitar.setDescription(this.description);
                        newGuitar.setModel(this.model);
                        newGuitar.setBrand(this.brand);
                        newGuitar.setPrice(this.price);

                        File newFile = new File(linuxPath.toString());
                        File[] list = newFile.listFiles();

                        for(File f : list){
                                if(f.setLastModified(System.currentTimeMillis())){
                                        absolutePath = f.getName();
                                        newGuitar.setImg(absolutePath);
                                }
                        }           

                        this.guitarDAO.add(newGuitar);

                        /*for(File f : list){
                                if(f.setLastModified(System.currentTimeMillis())){
                                        f.delete();
                                }
                        }*/
                
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
        /*public StreamedContent getFoto(){
                File foto = new File("suafoto.jpg");
                DefaultStreamedContent content=null;
                try{
                    BufferedInputStream in = new BufferedInputStream(new FileInputStream(foto));
                    byte[] bytes = new byte[in.available()];
                    in.read(bytes);
                    in.close();
                    content = new DefaultStreamedContent();
                }catch(Exception e){
                    e.printStackTrace();
                }
                return content;
        }*/
        
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