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
	
	public Modifier addPro(String description, float score) {
		Modifier newModifier = new Modifier(description, score);
		pros.add(newModifier);
		return newModifier;
	}
	
	public void removePro(int number) {
		pros.remove(number);
	}
	
	public ArrayList<Modifier> getPros() {
		return pros;
	}
	
	public Modifier addCon(String description, float score) {
		Modifier newModifier = new Modifier(description, score);
		cons.add(newModifier);
		return newModifier;
	}
	
	public void removeCon(int number) {
		cons.remove(number);
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