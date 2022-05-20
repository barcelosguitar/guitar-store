package br.com.barcelos_projects.service;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

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
        
        private Path linuxPath = Paths.get("/home/barcelos/git/guitar-store/src/main/webapp/resources/img/");
        //private Path winPath = Paths.get("C:\\Users\\Usuário\\git\\guitar-store\\src\\main\\webapp\\resources\\img");
        private String absolutePath;

        public void openNew(){
                this.selectedGuitar = new Guitar();
        }
        public void addGuitar() {
                try {
                        Guitar newGuitar = new Guitar();
                        newGuitar.setCode(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
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

                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto cadastrado"));
                
                } catch (Exception e) {
                        e.printStackTrace();
                }
                PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-guitars");
        }
        public void save(){
                if (this.selectedGuitar.getId() == 0) {
                        this.selectedGuitar.setCode(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
                        this.guitarDAO.add(this.selectedGuitar);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto Adicionado"));
                    }
                    else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto Atualizado"));
                    }
            
                    PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
                    PrimeFaces.current().ajax().update("form:messages", "form:dt-guitars");
                
        }
        public void remove(){
                try {
                        this.guitarDAO.delete(this.selectedGuitar);
                        this.selectedGuitar = null;
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto Removido"));
                        PrimeFaces.current().ajax().update("form:messages", "form:dt-guitars");
                       
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        public String getDeleteButtonMessage() {
                if (hasSelectedProducts()) {
                    int size = this.selectedGuitars.size();
                    return size > 1 ? size + " products selected" : "1 product selected";
                }
                return "Remover";
        }
        
        public boolean hasSelectedProducts() {
                return this.selectedGuitars != null && !this.selectedGuitars.isEmpty();
        }
        
        public void deleteSelectedGuitars() {
                this.guitarDAO.deleteAll(selectedGuitars);
                this.selectedGuitars = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Productos Removidos"));
                PrimeFaces.current().ajax().update("form:messages", "form:dt-guitars");
                PrimeFaces.current().executeScript("PF('dtGuitars').clearFilters()");
        }
        public List<Guitar> getGuitars(){
                return this.guitarDAO.listGuitars();
        }
        public List<Guitar> getRandomGuitarList(){
                return this.guitarDAO.listRandomGuitars();
        }

        //Getters and Setters

        public List<Guitar> getSelectedGuitars(){
                return selectedGuitars;
        }
        public void setSelectedGuitars(List<Guitar> selectedGuitars){
                this.selectedGuitars = selectedGuitars;
        }
        public Guitar getSelectedGuitar() {
                return selectedGuitar;
            }
        public void setSelectedProduct(Guitar selectedGuitar) {
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