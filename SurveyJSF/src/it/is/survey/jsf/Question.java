package it.is.survey.jsf;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class Question {
	private String Title;
	private byte[] image;

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	private StreamedContent convertImageToStream(byte[] bytes) {
		InputStream is = new ByteArrayInputStream(bytes);
		StreamedContent image = new DefaultStreamedContent(is);

		return image;
	}

	public StreamedContent getSCImage() {

		return convertImageToStream(getImage());
	}

	private List<Answer> items;
	private String id;

	private Argument argument;

	@PostConstruct
	public void init() {

		items = new ArrayList<Answer>();
	}

	public Question() {

		items = new ArrayList<Answer>();
	}

	public void addAnswer() {
		items.add(new Answer());
	}

	public void removeAnswer(Answer item) {
		items.remove(item);
	}

	public void save() {

	}

	public List<Answer> getItems() {
		return items;
	}

	public void setItems(List<Answer> items) {
		this.items = items;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public Argument getArgument() {
		return argument;
	}

	public void setArgument(Argument argument) {
		this.argument = argument;
	}

}
