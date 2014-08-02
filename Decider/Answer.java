import java.util.*;

public class Answer {
	private String answer;
	private ArrayList<Modifier> pros;
	private ArrayList<Modifier> cons;
	
	public Answer(String answer) {
		this.answer = answer;
		pros = new ArrayList<Modifier>();
		cons = new ArrayList<Modifier>();
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getAnswer() {
		return this.answer;
	}
	
	public void addPro(String description, float score) {
		pros.add(new Modifier(description, score));
	}
	
	public ArrayList<Modifier> getPros() {
		return pros;
	}
	
	public void addCon(String description, float score) {
		cons.add(new Modifier(description, score));
	}
	
	public ArrayList<Modifier> getCons() {
		return cons;
	}
	
	public float tallyScore() {
		float score=0.0f;
		for (Modifier pro:pros) {
			score+=pro.getScore();
		}
		for (Modifier con:cons) {
			score-=con.getScore();
		}
		return score;
	}
}