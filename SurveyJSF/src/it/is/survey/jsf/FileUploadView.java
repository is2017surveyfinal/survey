package it.is.survey.jsf;

import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

public class FileUploadView {

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext context = FacesContext.getCurrentInstance();
		FacesContext.getCurrentInstance().addMessage(null, message);
		FileUploaded fu = new FileUploaded();
		try {
			InputStream is = event.getFile().getInputstream();
			fu.setImageFile(is);
			context.getExternalContext().getSessionMap().put("fileUploaded", fu);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}