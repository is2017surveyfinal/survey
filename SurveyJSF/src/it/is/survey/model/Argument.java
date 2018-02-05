package it.is.survey.model;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Argument
 *
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Argument.findAll",    query="SELECT a FROM Argument a "),
    @NamedQuery(name="Argument.findById",   query="SELECT a FROM Argument a where a.Id= :Id"),
    @NamedQuery(name="Argument.findByTesto",query="SELECT a FROM Argument a where a.testo= :testo"),
    @NamedQuery(name="Argument.deleteById",  query="DELETE FROM Argument a where a.Id= :Id"),
}) 
public class Argument implements Serializable {

	   
	@Id
	@GeneratedValue
	private long Id;
	private String testo;
	private static final long serialVersionUID = 1L;
	/*@OneToMany
	private List<Question> questionList;*/

	public Argument() {
		super();
	}   
	public long getId() {
		return this.Id;
	}
	
    /*
	public List<Question> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}*/
	
	public void setId(long Id) {
		this.Id = Id;
	}   
	public String getTesto() {
		return this.testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}
   
}
