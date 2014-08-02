import java.util.*;

public class Question {
	private String question;
	private ArrayList<Answer> answers;
	private Date created;
	private Answer suggestedDecision;
	private Answer actualDecision;
	
	public Question(String question) {
		this.question = question;
		answers = new ArrayList<Answer>();
		created = new Date();
		suggestedDecision = null;
		actualDecision = null;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public Answer addAnswer(String answer) {
		Answer newAnswer = new Answer(answer);
		answers.add(newAnswer);
		return newAnswer;
	}
	
	public ArrayList<Answer> getAnswers() {
		return this.answers;
	}
	
	public void setSuggestedDecision(Answer suggestedDecision) {
		this.suggestedDecision = suggestedDecision;
	}
	
	public Answer getSuggestedDecision() {
		return this.suggestedDecision;
	}
	
	public void setActualDecision(Answer actualDecision) {
		this.actualDecision = actualDecision;
	}
	
	public Answer getActualDecision() {
		return this.actualDecision;
	}
}