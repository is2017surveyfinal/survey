package it.is.survey.model;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;
import org.eclipse.persistence.annotations.JoinFetch;
import static org.eclipse.persistence.annotations.JoinFetchType.INNER;

/**
 * Entity implementation class for Entity: survey
 *
 */
//select s from Survey s WHERE s.title='"+title+"'"


@Entity
@NamedQueries({
    @NamedQuery(name="Survey.findByTitle", query="SELECT s FROM Survey s where s.title= :title"),
    @NamedQuery(name="Survey.findById",    query="SELECT s FROM Survey s where s.Id= :Id"),
    @NamedQuery(name="Survey.findAll",     query="SELECT s FROM Survey s"),
    @NamedQuery(name="Survey.deleteById",  query="DELETE FROM Survey s where s.Id= :Id"),
    
}) 

public class Survey implements Serializable {

	@Id
	@GeneratedValue
	private int Id;
	@OneToMany (orphanRemoval = true, cascade = PERSIST)
	private List<Question> questionList;
/*	@ManyToMany(cascade = PERSIST)
	private List<Fruitore> fruitoreList;*/
	@Column(columnDefinition = "MEDIUMTEXT")
	private String XML;
	private boolean published;
	
	public List<Question> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}
/*	public List<Fruitore> getFruitoreList() {
		return fruitoreList;
	}
	public void setFruitoreList(List<Fruitore> fruitoreList) {
		this.fruitoreList = fruitoreList;
	}*/
	public int getId() {
		return Id;
	}
	
	public void setId(int iD) {
		Id = iD;
	}

	private String title;
	private static final long serialVersionUID = 1L;

	public Survey() {
		super();
	}   
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
   
	
	
	public Question createQuestion(String questionText)
	{
		Question q= new Question();
		q.setTesto(questionText);
		questionList.add(q);
		return q;
		
	}
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}
	public String getXML() {
		return XML;
	}
	public void setXML(String xML) {
		XML = xML;
	}
}
