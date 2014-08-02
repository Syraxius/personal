public class Modifier {
	private String description;
	private float score;
	
	public Modifier(String description, float score) {
		this.description = description;
		this.score = score;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setScore(float score) {
		this.score = score;
	}
	
	public float getScore() {
		return this.score;
	}
}