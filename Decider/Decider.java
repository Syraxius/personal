import java.util.*;

public class Decider {
	private ArrayList<Question> questions;
	
	public Decider() {
		questions = new ArrayList<Question>();
	}
	
	public Question addQuestion(String question) {
		Question newQuestion = new Question(question);
		questions.add(newQuestion);
		return newQuestion;
	}
	
	public ArrayList<Question> getQuestions() {
		return this.questions;
	}
}