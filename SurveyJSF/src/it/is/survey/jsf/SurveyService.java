package it.is.survey.jsf;

import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

public class SurveyService {

	private TreeNode root;

	public SurveyService() {

		FacesContext context = FacesContext.getCurrentInstance();
		SurveyProducer surveyProducer = (SurveyProducer) context.getExternalContext().getSessionMap()
				.get("surveyProducer");

		List<Question> questionList;
		List<Answer> questionItem;
		Iterator<Question> iteratorQuestion;
		Iterator<Answer> iteratorItem = null;
		TreeNode questionNode = null;
		TreeNode itemNode = null;
		root = new DefaultTreeNode("Root node", null);
		TreeNode child = new DefaultTreeNode("Child Node", this.root);
		questionList = surveyProducer.getSelectedSurvey().getQuestions();
		iteratorQuestion = questionList.iterator();
		while (iteratorQuestion.hasNext()) {

			questionNode = new DefaultTreeNode(iteratorQuestion.next().getTitle(), root);
			while (iteratorItem.hasNext()) {
				itemNode = new DefaultTreeNode(iteratorItem.next().getValue(), questionNode);
			}

		}

	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

}
