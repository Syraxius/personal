package ce1;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TextBuddyTUI {

	// Command Strings

	private static final String COMMAND_EXIT = "exit";
	private static final String COMMAND_CLEAR = "clear";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_DISPLAY = "display";
	private static final String COMMAND_ADD = "add";

	// Error Strings

	private static final String ERROR_ARGUMENT_COUNT = "Wrong argument count supplied.";
	private static final String ERROR_EXTENSION = "Invalid file extension.";
	private static final String ERROR_FILE_READY = "There has been a filesystem error.";
	
	private static final String ERROR_COMMAND = "Command is invalid.";
	private static final String ERROR_ADD = "Adding has failed.";
	private static final String ERROR_DELETE = "Deleting has failed.";
	private static final String ERROR_CLEAR = "Clearing has failed.";

	// Printed Strings

	private static final String FORMAT_WELCOME = "Welcome to TextBuddy. %1$s is ready for use\n";
	private static final String FORMAT_CLEAR = "all content deleted from %1$s";
	private static final String FORMAT_ADD = "added to %1$s: \"%2$s\"";
	private static final String FORMAT_EMPTY = "%1$s is empty";
	private static final String FORMAT_DELETE = "deleted from %1$s: \"%2$s\"";
	private static final String FORMAT_DISPLAY = "%1$d. %2$s\n";
	private static final String COMMAND_PROMPT = "command: ";

	// Other Constants

	private static final String VALID_FILENAME = ".txt";
	
	// Objects Declaration

	TextBuddyLogic logic;
	Scanner scanner;

	// Constructors

	public TextBuddyTUI(String[] args) {
		systemAssertArguments(args);
		systemInitialize(args);
	}

	// Initialization / Clean Up Methods

	private void systemAssertArguments(String[] args) {
		exitIfInvalidArgumentCount(args);
		exitIfInvalidFileName(args[0]);
	}
	
	private void systemInitialize(String[] args) {
		initializeLogic(args);
		initializeTUI();
	}

	private void exitIfInvalidFileName(String fileName) {
		exitIfIncorrectExtension(fileName);
	}

	private void exitIfIncorrectExtension(String fileName) {
		boolean endsWithCorrectExtension = fileName.endsWith(VALID_FILENAME);
		if (!endsWithCorrectExtension) {
			printErrorAndExit(ERROR_EXTENSION);
		}
	}

	private void exitIfInvalidArgumentCount(String[] args) {
		boolean onlyOneArgument = (args.length == 1);
		if (!onlyOneArgument) {
			printErrorAndExit(ERROR_ARGUMENT_COUNT);
		}
	}

	private void initializeLogic(String[] args) {
		logic = new TextBuddyLogic(args[0]);
	}

	private void initializeTUI() {
		scanner = new Scanner(System.in);
	}

	private void systemSave() {
		logic.save();
	}

	private void systemCleanUp() {
		scanner.close();
	}

	// UI Methods

	public void systemRun() {
		if (isFileReady()) {
			printReadyMessage();
			interactiveUntilUserExits();
		} else {
			printFileReadyError();
		}
	}

	private boolean isFileReady() {
		return logic.isFileReadableWritable();
	}

	private void printFileReadyError() {
		System.out.println(ERROR_FILE_READY);
	}

	private void interactiveUntilUserExits() {
		while (true) {
			String feedback = processUserCommand();
			systemSave();
			System.out.println(feedback);
		}
	}

	private String processUserCommand() {
		String command = getUserCommand();
		switch (command) {
		case COMMAND_ADD:
			return add();
		case COMMAND_DISPLAY:
			return display();
		case COMMAND_DELETE:
			return delete();
		case COMMAND_CLEAR:
			return clear();
		case COMMAND_EXIT:
			exit();
		default:
			discardLine();
			return ERROR_COMMAND;
		}
	}

	// Operation Methods

	private String add() {
		String addedLine = getValidLine();
		boolean addSuccess = logic.add(addedLine);
		if (addSuccess) {
			return String.format(FORMAT_ADD, logic.getFileName(), addedLine);
		} else {
			return ERROR_ADD;
		}
	}

	private String display() {
		List<String> list = logic.getList();
		if (list.isEmpty()) {
			return String.format(FORMAT_EMPTY, logic.getFileName());
		} else {
			return listToString(list);
		}
	}

	private String delete() {
		int lineNumber = getValidInt(1, Integer.MAX_VALUE);
		String deletedLine = logic.delete(lineNumber);
		boolean deleteSuccess = deletedLine != null;
		if (deleteSuccess) {
			return String.format(FORMAT_DELETE, deletedLine);
		} else {
			return ERROR_DELETE;
		}
	}

	private String clear() {
		boolean clearSuccess = logic.clear();
		if (clearSuccess) {
			return String.format(FORMAT_CLEAR, logic.getFileName());
		} else {
			return ERROR_CLEAR;
		}
	}

	private void exit() {
		systemCleanUp();
		systemExit();
	}

	private void systemExit() {
		System.exit(0);
	}

	private void discardLine() {
		scanner.nextLine();
	}

	// Input Methods

	private String getValidLine() {
		return scanner.nextLine().trim();
	}

	private int getValidInt(int min, int max) {
		try {
			int value = scanner.nextInt();
			if (min <= value && value <= max) {
				return value;
			} else {
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			discardLine();
			return -1;
		}
	}

	// Printing Methods

	private void printErrorAndExit(String errorMessage) {
		System.out.println(errorMessage);
		System.exit(1);
	}

	private void printReadyMessage() {
		System.out.printf(FORMAT_WELCOME, logic.getFileName());
	}

	private String getUserCommand() {
		System.out.print(COMMAND_PROMPT);
		String userCommand = scanner.next();
		return userCommand;
	}

	private String listToString(List<String> list) {
		String consolidateString = "";
		int lineNumber = 1;
		for (String content : list) {
			consolidateString += String.format(FORMAT_DISPLAY, lineNumber, content);
			lineNumber++;
		}
		String trimmedString = consolidateString.trim();
		return trimmedString;
	}
}
