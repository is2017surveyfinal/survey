package it.is.survey.jsf;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class PictureBean {

	private StreamedContent myImage;

	public PictureBean() {

	}

	public StreamedContent getImage() {
		try {
			System.out.println("ContentLenght***** in byte" + IOUtils.toByteArray(myImage.getStream()).length);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return myImage;
	}

	public void setImage(InputStream inputStream) {
		myImage = new DefaultStreamedContent(inputStream, "image/png");
	}

}