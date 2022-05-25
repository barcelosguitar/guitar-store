package br.com.barcelos_projects.service;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
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

@Named ("guitarBean")
@ApplicationScoped
public class GuitarBean implements Serializable{

        private String code;
        private String name;
        private String description;
        private Model model;
        private Brand brand;
        private Double price;
        private String img;
        private Integer quantity;
        private List<Guitar> guitars;
        private List<Guitar> selectedGuitars;
        private Guitar selectedGuitar;

        @Inject
        private GuitarDAO guitarDAO;
        
        private Path linuxPath = Paths.get("/home/barcelos/git/guitar-store/src/main/webapp/resources/img");
        //private Path winPath = Paths.get("C:\\Users\\Usuário\\git\\guitar-store\\src\\main\\webapp\\resources\\img");
        private String fileName;

        @PostConstruct
        public void init (){
                guitars = this.guitarDAO.listGuitars();
        }
        public void openNew(){
                this.selectedGuitar = new Guitar();
        }
        public void save() {
                try {
                        if (this.selectedGuitar.getCode() == null) {
                                Guitar newGuitar = new Guitar();
                                newGuitar.setCode(UUID.randomUUID().toString()
                                        .toUpperCase().replaceAll("-", "")
                                        .substring(0, 9));
                                newGuitar.setName(selectedGuitar.getName());
                                newGuitar.setDescription(selectedGuitar.getDescription());
                                
                                newGuitar.setModel(selectedGuitar.getModel());
                                newGuitar.setBrand(selectedGuitar.getBrand());
                                newGuitar.setPrice(selectedGuitar.getPrice());
                                newGuitar.setQuantity(selectedGuitar.getQuantity());

                                File newFile = new File(linuxPath.toString());
                                File[] list = newFile.listFiles();

                                for(File f : list){
                                        if(f.setLastModified(System.currentTimeMillis())){
                                                fileName = f.getName();
                                                newGuitar.setImg(fileName);
                                        }
                                }           
                                this.guitarDAO.add(newGuitar);

                                FacesContext.getCurrentInstance().addMessage(null, 
                                        new FacesMessage("Secesso!", "Produto cadastrado!"));
                        } else {
                                this.selectedGuitar.setImg(selectedGuitar.getImg());
                                this.selectedGuitar.setCode(selectedGuitar.getCode());
                                this.selectedGuitar.setName(selectedGuitar.getName());
                                this.selectedGuitar.setDescription(selectedGuitar.getDescription());
                                this.selectedGuitar.setModel(selectedGuitar.getModel());
                                this.selectedGuitar.setBrand(selectedGuitar.getBrand());
                                this.selectedGuitar.setPrice(selectedGuitar.getPrice());
                                this.selectedGuitar.setQuantity(selectedGuitar.getQuantity());

                                this.guitarDAO.update(this.selectedGuitar);
                                FacesContext.getCurrentInstance().addMessage(null, 
                                        new FacesMessage("Secesso!", "Produto cadastrado!"));

                                PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
                        }
                
                } catch (NullPointerException e) {
                        FacesContext.getCurrentInstance().addMessage(null, 
                                        new FacesMessage("Atenção!", e.getMessage()));
                        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
                } catch (Exception e) {
                        FacesContext.getCurrentInstance().addMessage(null, 
                                        new FacesMessage("Atenção!", e.getMessage()));
                        e.printStackTrace();
                }
                PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        }
        public void remove(){
                try {
                        File deleteImgFile = new File(linuxPath+this.selectedGuitar.getImg());
                        deleteImgFile.delete();
                        this.guitarDAO.delete(this.selectedGuitar);
                        this.selectedGuitar = null;
                        
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso!","Produto removido"));
                        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
                       
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        public String getDeleteButtonMessage() {
                if (hasSelectedProducts()) {
                    int size = this.selectedGuitars.size();
                    return size > 1 ? size + " produtos selecionados" : "1 produto selecionado";
                }
                return "Deletar";
        }
        
        public boolean hasSelectedProducts() {
                return this.selectedGuitars != null && !this.selectedGuitars.isEmpty();
        }
        
        public void deleteSelectedGuitars() {
                File file = new File(linuxPath.toString());
                File[] fileList = file.listFiles();
                for(Guitar guitar : this.selectedGuitars){
                        if(file.getPath().equals(guitar.getImg())){
                                file.delete();
                        }
                        if(selectedGuitars.contains(guitar)){
                                this.guitarDAO.delete(guitar);
                        }
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Productos removidos!"));
                PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
                PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
        }
        public List<Guitar> listAllGuitars(){
                return this.guitarDAO.listGuitars();
        }
        public List<Guitar> getRandomGuitarList(){
                return this.guitarDAO.listRandomGuitars();
        }

        //Getters and Setters
        public List<Guitar> getGuitars(){
                guitars = this.guitarDAO.listGuitars();
                return guitars;
        }
        public void setGuitars(List<Guitar> guitars){
                this.guitars = guitars;
        }
        public List<Guitar> getSelectedGuitars(){
                return selectedGuitars;
        }
        public void setSelectedGuitars(List<Guitar> selectedGuitars){
                this.selectedGuitars = selectedGuitars;
        }
        public Guitar getSelectedGuitar() {
                return selectedGuitar;
            }
        public void setSelectedGuitar(Guitar selectedGuitar) {
                this.selectedGuitar = selectedGuitar;
        }
        public String getCode() {
                return code;
        }
        public void setCode(String code) {
                this.code = code;
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
        public String getModel() {
                return model.getDescription();
        }
        public void setModel(Model model) {
                this.model = model;
        }
        public String getBrand() {
                return brand.getDescription();
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
                File img = new File(linuxPath.toString());
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
        public Integer getQuantity() {
                return quantity;
        }
        public void setQuantity(Integer quantity) {
                this.quantity = quantity;
        }
}