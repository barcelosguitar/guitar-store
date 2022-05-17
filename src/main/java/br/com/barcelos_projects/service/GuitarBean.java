package br.com.barcelos_projects.service;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.barcelos_projects.enums.Brand;
import br.com.barcelos_projects.enums.Model;
import br.com.barcelos_projects.model.Guitar;
import br.com.barcelos_projects.repository.GuitarDAO;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

@RequestScoped
@Named ("guitar")
public class GuitarBean implements Serializable{

        private String name;
        private String description;
        private Model model;
        private Brand brand;
        private Double price;
        private String img;

        @Inject
        private GuitarDAO guitarDAO;
        
        private UploadedFile file;
        private List<Guitar> guitars;

        private Path folder = Paths.get("/home/barcelos/Pictures/GuitarStore/guitars");

        public void handleImgUpload(FileUploadEvent event) {
                file = event.getFile();

                FacesMessage msg = new FacesMessage("Sucesso", event.getFile().getFileName() + " foi enviado.");
                FacesContext.getCurrentInstance().addMessage(null, msg);

                        try {
                                String fileName = FilenameUtils.getName(file.getFileName());
                                String extension = FilenameUtils.getExtension(file.getFileName());
                                Path filePath = Files.createTempFile(folder, fileName + "-", "." + extension);

                                InputStream input = file.getInputStream();
                                Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
                                
                        } catch (IOException e){
                                e.getMessage();
                        }
        }

        public void addGuitar() {
        //if(name!=null && description!=null && model!=null && brand!=null && price!=0.0) {
                Guitar newGuitar = new Guitar();
                newGuitar.setName(this.name);
                newGuitar.setDescription(this.description);
                newGuitar.setModel(this.model);
                newGuitar.setBrand(this.brand);
                newGuitar.setPrice(this.price);

                File newFile = new File(folder.toString());
                File[] list = newFile.listFiles();

                for(File f : list){
                        if(f.setLastModified(System.currentTimeMillis())){
                                newGuitar.setImg(f.getAbsolutePath());
                        }
                }
                
                //if(newGuitar!=null)
                this.guitarDAO.add(newGuitar);
                //}
        }
        public List<Guitar> getGuitars(){
                guitars = guitarDAO.listAll();
                return guitars;
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
        public String getImg() {
                return img;
        }
        public void setImg(String img) {
                this.img = img;
        }
}