package test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import it.is.survey.facade.SurveyFacade;
import it.is.survey.jsf.Answer;
import it.is.survey.jsf.PictureBean;
import it.is.survey.jsf.Question;
import it.is.survey.jsf.Survey;
import it.is.survey.jsf.SurveyProducer;
import it.is.survey.jsf.myRequired;


public class TreeManagedBean {
	// TreeNode instance
	private List<Survey> surveys;
	private TreeNode root;
    private TreeNode selectedNode;
    private SurveyProducer surveyProducer;
     
	
    
    
    public TreeManagedBean(){
		
		FacesContext context = FacesContext.getCurrentInstance();
    	surveyProducer = (SurveyProducer) context.getExternalContext().getSessionMap().get("surveyProducer");
    	
    	Question q=null;
    	Answer i=null;
    	List<Question> questionList;
		List<Answer> questionItem;
    	Iterator<Question> iteratorQuestion;
		Iterator<Answer> iteratorItem = null;
		TreeNode child;
		TreeNode descendent;
				
		questionList=surveyProducer.getSelectedSurvey().getQuestions();
	    System.out.println("----questionList"+questionList);
	    iteratorQuestion=questionList.iterator();
	    this.root = new DefaultTreeNode("Root Node", null);
	    while(iteratorQuestion.hasNext()) {
	        q=iteratorQuestion.next();
	        child = new DefaultTreeNode(q, root);
	    	iteratorItem= q.getItems().iterator();
	      	
            System.out.println("----Question: "+q);
            System.out.println("----iteratorItem: "+iteratorItem);
           
            
            while (iteratorItem.hasNext()){
            	
            	i=iteratorItem.next();
            	descendent=new DefaultTreeNode(i,child);
            	System.out.println("Item:"+i);
            }
      
	     
	
}

		
	
	
	
	}
    


	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}


	public TreeNode getSelectedNode() {
        return selectedNode;
    }
 
    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }
	 
	public void remove(TreeNode node){
		System.out.println("Node: "+node.toString());
		
	}
	
	public void test(){
		System.out.println("sono in test:");
	}
	
	/*public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Document Edited", ((TreeNode) event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((TreeNode) event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    */
	
	// old version displaySelectedSingle new version modifySelectedSingle
    public String modifySelectedSingle() {
    	System.out.println("XXXXXXXXXXXXXXXXXXXXX");
    	//myrequired.setRequired(false);
    	Object dataRetrieved;
    	Question q;
    	String result="Non inizializzata";
        if(selectedNode != null) {
        	dataRetrieved=selectedNode.getData();
        	if (dataRetrieved instanceof Question) {
        		System.out.println("Cast toQuestion");
        		result=((Question) dataRetrieved).getTitle();
        		deleteQuestion(dataRetrieved);
        	} 
        	if (dataRetrieved instanceof Answer) {
        		System.out.println("Cast toItem");
        		q= ((Question )selectedNode.getParent().getData());
        		result=((Answer) dataRetrieved).getTitle()+":"+q.getTitle();
        		deleteAnswer(dataRetrieved,q);
        	} 
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", result);
            FacesContext.getCurrentInstance().addMessage(null, message);
            
        }
        return "sformquestion?faces-redirect=true";
    }
    
    
    public void ViewSelectedSingle() {
    	Object dataRetrieved;
    	Question q;
    	String result="Non inizializzata";
        if(selectedNode != null) {
        	dataRetrieved=selectedNode.getData();
        	if (dataRetrieved instanceof Question) {
        		System.out.println("Cast toQuestion");
        		result=((Question) dataRetrieved).getTitle();
        		//deleteQuestion(dataRetrieved);
        		
        		System.out.println("1");
        		InputStream inputStream = new ByteArrayInputStream(((Question) dataRetrieved).getImage());
        		System.out.println("1");
        		PictureBean pb= new PictureBean();
        		System.out.println("1");
        		pb.setImage(inputStream);
        		
        		
        	
        		FacesContext context = FacesContext.getCurrentInstance();
            	context.getExternalContext().getSessionMap().put("pictureBean",pb);
            	System.out.println("immagine*******:"+pb);
            	
            	 Map<String,Object> options = new HashMap<String, Object>();
                 options.put("resizable", false);
                 options.put("draggable", false);
                 options.put("modal", true);
                 RequestContext.getCurrentInstance().openDialog("viewimage", options, null);
                 System.out.println("Fuori da request context");
        	} 
        	
            //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", result);
            //FacesContext.getCurrentInstance().addMessage(null, message);
            
        }
        
    }
    
    private void deleteQuestion(Object question) {
    	 List<Question> ql=surveyProducer.getSelectedSurvey().getQuestions();
    	 System.out.println("In delete Question:  "+question);
    	 ql.remove(question);
    }
    
    private void deleteAnswer(Object item,Question question) {
    List<Answer> la=question.getItems();
    System.out.println("In delete Item:  "+item);
    la.remove(item);
   }
   
    
    private void deleteAnswer() {
    	
    }
     /*
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    } 
	*/
	
	    } 
	
	
