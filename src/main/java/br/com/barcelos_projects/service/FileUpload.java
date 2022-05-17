/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.barcelos_projects.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;
import org.primefaces.util.EscapeUtils;

/**
 *
 * @author barcelos
 */
@RequestScoped
@Named ("fileUpload")
public class FileUpload {
    
    private UploadedFile file;
    private String dropZoneText = "Drop zone p:inputTextarea demo.";
    
    public void upload(FileUploadEvent event) {
        
        file = event.getFile();

        if (file != null) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public void handleFileUpload(FileUploadEvent event) {
        file = event.getFile();
        
        FacesMessage msg = new FacesMessage("Sucesso", event.getFile().getFileName() + " foi enviado.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        try {
           
            Path folder = Paths.get("/home/barcelos/Pictures/GuitarStore/guitars");
            String filename = file.getFileName(); 
            String extension = FilenameUtils.getExtension(file.getFileName());
            Path filePath = Files.createTempFile(folder, filename + "-", "." + extension);
            
            InputStream input = file.getInputStream();
            Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);

        } catch(IOException e) {
            e.getMessage();
        }
    }
    public void handleFileUploadTextarea(FileUploadEvent event) {
        String jsVal = "PF('textarea').jq.val";
        String fileName = EscapeUtils.forJavaScript(event.getFile().getFileName());
        PrimeFaces.current().executeScript(jsVal + "(" + jsVal + "() + '\\n\\n" + fileName + " uploaded.')");
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getDropZoneText() {
        return dropZoneText;
    }

    public void setDropZoneText(String dropZoneText) {
        this.dropZoneText = dropZoneText;
    }
}
