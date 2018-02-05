package test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import it.is.survey.jsf.Answer;

public class Bean {

    private List<Answer> items;

    @PostConstruct
    public void init() {
        items = new ArrayList<Answer>();
    }

    public void add() {
        items.add(new Answer());
    }

    public void remove(Answer item) {
        items.remove(item);
    }

    public void save() {
        System.out.println("rispotste: " + items);
        
    }

    public List<Answer> getItems() {
        return items;
    }

}