package it.is.survey.jsf;

public class Answer {

    private String value;

    private long id;
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return String.format("Item[value=%s]", value);
    }

    public String getTitle(){
    	return value;
    }
    
    public void setTitle(String value){
    	this.value=value;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}