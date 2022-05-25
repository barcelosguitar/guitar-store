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
import org.primefaces.util.EscapeUtils;

@RequestScoped
@Named ("fileUpload")
public class FileUpload {
    
    private UploadedFile file;
    private String dropZoneText = "Drop zone p:inputTextarea demo.";
    private Path linuxPath = Paths.get("/home/barcelos/git/guitar-store/src/main/webapp/resources/img");
    //private Path winPath = Paths.get("C:\\Users\\Usuário\\git\\guitar-store\\src\\main\\webapp\\resources\\img");
    
    public void upload(FileUploadEvent event) {
        
        file = event.getFile();

        if (file != null) {
            FacesMessage message = new FacesMessage("Sucesso", file.getFileName() + " foi enviado.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public void handleFileUpload(FileUploadEvent event) {
        file = event.getFile();
        
        FacesMessage msg = new FacesMessage("Sucesso", event.getFile().getFileName() + " foi enviado.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        try {
            Path filePath = Files.createTempFile(linuxPath, "" + "", ".png");
            
            InputStream input = file.getInputStream();
            Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);

        } catch(IOException e) {
            e.getMessage();
        }
    }
    public void handleFileUploadTextarea(FileUploadEvent event) {
        String jsVal = "PF('textarea').jq.val";
        String fileName = EscapeUtils.forJavaScript(event.getFile().getFileName());
        PrimeFaces.current().executeScript(jsVal + "(" + jsVal + "() + '\\n\\n" + fileName + " Enviado.')");
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
