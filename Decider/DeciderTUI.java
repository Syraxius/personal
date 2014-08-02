import java.util.*;

public class DeciderTUI {
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Decider decider = new Decider();
		int choice;
		do {
			System.out.println();
			System.out.println("---------");
			System.out.println("Main Menu");
			System.out.println("---------");
			System.out.println();
			System.out.println("1) Add Question");
			System.out.println("2) Remove Questions");
			System.out.println("3) Manage Questions");
			System.out.println("4) View Questions");
			System.out.println("5) Exit");
			System.out.println();
			System.out.print("Please select your choice: ");
			choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
				case 1:
					add(decider);
				break;
				case 2:
					remove(decider);
				break;
				case 3:
					manage(decider);
				break;
				case 4:
					view(decider);
				break;
				case 5:
					System.out.println();
					System.out.println("Thank you for using Decider!");
				break;
			}
		} while (choice!=5);
	}
	
	private static void remove(Decider decider) {
		System.out.println();
		System.out.println("----------------");
		System.out.println("Remove Questions");
		System.out.println("----------------");
		ArrayList<Question> questions = decider.getQuestions();
		System.out.println();
		if (questions.size()==0) {
			System.out.println("There are no questions defined yet!");
		} else {
			int i=0;
			for (Question question:questions) {
				System.out.println(++i+") "+question.getQuestion());
			}
			System.out.println(i+1+") Return to Main Menu");
			System.out.println();
			System.out.print("Please select your choice: ");
			int choice=sc.nextInt();
			if (choice!=i+1) {
				decider.removeQuestion(choice-1);
			}
		}
	}
	
	private static void remove(Question question) {
		System.out.println();
		System.out.println("--------------");
		System.out.println("Remove Answers");
		System.out.println("--------------");
		ArrayList<Answer> answers = question.getAnswers();
		System.out.println();
		if (answers.size()==0) {
			System.out.println("There are no answers defined yet!");
		} else {
			int i=0;
			for (Answer answer:answers) {
				System.out.println(++i+") "+answer.getAnswer());
			}
			System.out.println(i+1+") Return to Main Menu");
			System.out.println();
			System.out.print("Please select your choice: ");
			int choice=sc.nextInt();
			if (choice!=i+1) {
				question.setSuggestedDecision(null);
				question.setActualDecision(null);
				question.removeAnswer(choice-1);
			}
		}
	}
	
	private static void add(Decider decider) {
		System.out.println();
		System.out.println("------------");
		System.out.println("Add Question");
		System.out.println("------------");
		System.out.println();
		System.out.println("Please enter a question:");
		Question newQuestion = decider.addQuestion(sc.nextLine());
		System.out.println();
		System.out.println("Question successfully added!");
		manage(newQuestion);
	}
	
	public static void add(Question question) {
		System.out.println();
		System.out.println("----------");
		System.out.println("Add Answer");
		System.out.println("----------");
		System.out.println();
		System.out.println("Please enter an answer:");
		question.addAnswer(sc.nextLine());
		System.out.println();
		System.out.println("Answer successfully added!");
	}
	
	private static void manage(Decider decider) {
		System.out.println();
		System.out.println("----------------");
		System.out.println("Manage Questions");
		System.out.println("----------------");
		ArrayList<Question> questions = decider.getQuestions();
		System.out.println();
		if (questions.size()==0) {
			System.out.println("There are no questions defined yet!");
		} else {
			int i=0;
			for (Question question:questions) {
				System.out.println(++i+") "+question.getQuestion());
			}
			System.out.println(i+1+") Return to Main Menu");
			System.out.println();
			System.out.print("Please select your choice: ");
			int choice=sc.nextInt();
			if (choice!=i+1) {
				manage(questions.get(choice-1));
			}
		}
	}
	
	private static void manage(Question question) {
		int choice;
		do {
		System.out.println();
		System.out.println("---------------");
		System.out.println("Manage Question");
		System.out.println("---------------");
		System.out.println(question.getQuestion());
		System.out.println();
		System.out.println("1) Add Answer");
		System.out.println("2) Remove Answers");
		System.out.println("3) Manage Answers");
		System.out.println("4) Random Decision");
		System.out.println("5) Tallied Decision");
		System.out.println("6) Return to Main Menu");
		System.out.println();
		System.out.print("Please select your choice: ");
		choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
			case 1:
				add(question);
			break;
			case 2:
				remove(question);
			break;
			case 3:
				manageAnswers(question);
			break;
			case 4:
				random(question);
			break;
			case 5:
				tally(question);
			break;
		}
		} while (choice!=6);
	}
	
	private static void manage(Answer answer) {
		int choice;
		do {
			System.out.println();
			System.out.println("-------------");
			System.out.println("Manage Answer");
			System.out.println("-------------");
			System.out.println(answer.getAnswer());
			System.out.println();
			System.out.println("1) Add Pro");
			System.out.println("2) Add Con");
			System.out.println("3) Return to Manage Answers");
			System.out.println();
			System.out.print("Please select your choice: ");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
				case 1:
					System.out.println("Description:");
					String description=sc.nextLine();
					System.out.print("Score: ");
					float score=sc.nextFloat();
					answer.addPro(description,score);
					System.out.println();
					System.out.println("Pro successfully added.");
				break;
				case 2:
					System.out.println("Description:");
					description=sc.nextLine();
					System.out.print("Score: ");
					score=sc.nextFloat();
					answer.addCon(description,score);
					System.out.println();
					System.out.println("Con successfully added.");
				break;
			}
		} while (choice!=3);
	}
	
	private static void manageAnswers(Question question) {
		System.out.println();
		System.out.println("--------------");
		System.out.println("Manage Answers");
		System.out.println("--------------");
		ArrayList<Answer> answers = question.getAnswers();
		if (answers.size()==0) {
			System.out.println("There are no answers defined yet!");
		} else {
			int i=0;
			for (Answer answer:answers) {
				System.out.println(++i+") "+answer.getAnswer());
			}
			System.out.println(i+1+") Return to Manage Question");
			System.out.println();
			System.out.print("Please select your choice: ");
			int choice=sc.nextInt();
			sc.nextLine();
			if (choice!=i+1) {
				manage(answers.get(choice-1));
			}
		}
	}
	
	public static void random(Question question) {
		System.out.println();
		System.out.println("---------------");
		System.out.println("Random Decision");
		System.out.println("---------------");
		ArrayList<Answer> answers = question.getAnswers();
		if (answers.size()==0) {
			System.out.println("There are no answers defined yet!");
		} else {
			Random rand = new Random();
			int random = 1+rand.nextInt(answers.size());
			int i=0;
			for (Answer answer:answers) {
				System.out.print(++i+") "+answer.getAnswer());
				if (i==random) {
					System.out.println(" ***Suggested Answer***");
					question.setSuggestedDecision(answers.get(i-1));
				} else {
					System.out.println();
				}
			}
			System.out.println(i+1+") Return to Manage Question");
			System.out.println();
			System.out.print("Please select your decision: ");
			int choice=sc.nextInt();
			sc.nextLine();
			if (choice!=i+1) {
				question.setActualDecision(answers.get(choice-1));
			}
		}
	}
	
	public static void tally(Question question) {
		System.out.println();
		System.out.println("----------------");
		System.out.println("Tallied Decision");
		System.out.println("----------------");
		ArrayList<Answer> answers = question.getAnswers();
		if (answers.size()==0) {
			System.out.println("There are no answers defined yet!");
		} else {
			float[] scoreList = new float[answers.size()];
			int i=0;
			float maxScore=0;
			int maxIdx=0;
			for (Answer answer:answers) {
				float currentScore = answer.tallyScore();
				scoreList[i++]=currentScore;
				if (currentScore>maxScore) {
					maxScore = currentScore;
					maxIdx = i-1;
				}
			}
			i=0;
			for (Answer answer:answers) {
				System.out.print(++i+") "+answer.getAnswer());
				System.out.print("("+scoreList[i-1]+")");
				if (i==maxIdx+1) {
					System.out.println(" ***Suggested Answer***");
					question.setSuggestedDecision(answers.get(i-1));
				} else {
					System.out.println();
				}
			}
			System.out.println(i+1+") Return to Manage Question");
			System.out.println();
			System.out.print("Please select your decision: ");
			int choice=sc.nextInt();
			sc.nextLine();
			if (choice!=i+1) {
				question.setActualDecision(answers.get(choice-1));
			}
		}
	}
	
	public static void view(Decider decider) {
		System.out.println();
		System.out.println("--------------");
		System.out.println("View Questions");
		System.out.println("--------------");
		ArrayList<Question> questions = decider.getQuestions();
		if (questions.size()==0) {
			System.out.println();
			System.out.println("There are no questions defined yet!");
		} else {
			for (Question question:questions) {
				System.out.println();
				System.out.println(question.getQuestion());
				ArrayList<Answer> answers = question.getAnswers();
				if (answers.size()==0) {
					System.out.println("\tThere are no answers defined yet!");
				} else {
					int i=0;
					for (Answer answer: answers) {
						System.out.println("\t"+(++i)+") "+answer.getAnswer());
					}
					if (question.getSuggestedDecision()!=null) {
						System.out.println("\tSuggested Decision: "+question.getSuggestedDecision().getAnswer());
					}
					if (question.getActualDecision()!=null) {
						System.out.println("\tActual Decision: "+question.getActualDecision().getAnswer());
					}
				}
			}
		}
	}
}