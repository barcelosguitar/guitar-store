package br.com.barcelos_projects.service;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.enterprise.context.SessionScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.barcelos_projects.enums.Brand;
import br.com.barcelos_projects.enums.Model;
import br.com.barcelos_projects.model.Guitar;
import br.com.barcelos_projects.repository.GuitarDAO;
import jakarta.annotation.PostConstruct;

import java.io.File;
import java.io.InputStream;
import java.util.List;

@SessionScoped
@Named ("guitar")
public class GuitarBean implements Serializable{

        private String name;
        private String description;
        private Model model;
        private Brand brand;
        private Double price;
        private StreamedContent img;

        @Inject
        private GuitarDAO guitarDAO;
        //private List<Guitar> guitars;

        //private Path linuxPath = Paths.get("/home/barcelos/Pictures/GuitarStore/guitars");
        private Path winPath = Paths.get("C:\\Users\\Usuário\\Pictures\\GuitarStore\\guitars");

        /*@PostConstruct
        public void init(){
                try {
                        
                } catch (Exception e) {
                        e.printStackTrace();
                }
                
        }*/
        public void addGuitar() {
        //if(name!=null && description!=null && model!=null && brand!=null && price!=0.0) {
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
                                newGuitar.setImg(f.getPath());
                        }
                }           
                //if(newGuitar!=null)
                this.guitarDAO.add(newGuitar);
                //}
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
        //TODO:
        public StreamedContent getImg () {
                Guitar findGuitar = guitarDAO.findByIdLong(id);
                if(findGuitar.getId().equals(id)){
                        InputStream input = this.getClass().getResourceAsStream(findGuitar.getImg());
                        return new DefaultStreamedContent(input);
                }   
        }
        public void setImg(StreamedContent img) {
                this.img = img;
        }
}