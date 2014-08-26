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
	private static final String ERROR_FILE_NAME_LENGTH = "File name should be at least 1 character long.";
	private static final String ERROR_FILE_RW = "There has been a filesystem error.";

	private static final String ERROR_COMMAND = "Command is invalid.";
	private static final String ERROR_ADD = "Adding has failed.";
	private static final String ERROR_DELETE = "Deleting has failed.";
	private static final String ERROR_CLEAR = "Clearing has failed.";

	// Error Codes

	private static final int INVALID_INTEGER = -1;
	
	// Printed Strings

	private static final String FORMAT_WELCOME = "Welcome to TextBuddy. %1$s is ready for use\n";
	private static final String FORMAT_CLEAR = "all content deleted from %1$s";
	private static final String FORMAT_ADD = "added to %1$s: \"%2$s\"";
	private static final String FORMAT_EMPTY = "%1$s is empty";
	private static final String FORMAT_DELETE = "deleted from %1$s: \"%2$s\"";
	private static final String FORMAT_DISPLAY = "%1$d. %2$s\n";
	private static final String COMMAND_PROMPT = "command: ";

	// Other Constants

	private static final String VALID_EXTENSION = ".txt";

	// Objects Declaration

	TextBuddyLogic logic;
	Scanner scanner;

	// Constructors

	public TextBuddyTUI(String[] args) {
		systemAssertArguments(args);
		systemInitialize(args);
		systemAssertReady();
	}

	// High Level System Implementation


	private void systemAssertArguments(String[] args) {
		exitIfInvalidArgumentCount(args);
		exitIfInvalidFileName(args);
	}

	private void systemInitialize(String[] args) {
		initializeLogic(args);
		initializeTUI();
	}
	
	private void systemAssertReady() {
		exitIfFilesystemError();
	}

	public void systemRun() {
		printWelcomeMessage();
		interactiveUntilUserExits();
		printFileReadyError();
	}

	private void interactiveUntilUserExits() {
		while (true) {
			String feedback = processUserCommand();
			systemSave();
			systemPrint(feedback);
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

	// Basic Operations

	private String getUserCommand() {
		System.out.print(COMMAND_PROMPT);
		String userCommand = scanner.next();
		return userCommand;
	}

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
			return String.format(FORMAT_DELETE, logic.getFileName(),
					deletedLine);
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

	// Initialization and Clean Up

	private void exitIfInvalidArgumentCount(String[] args) {
		boolean onlyOneArgument = (args.length == 1);
		if (!onlyOneArgument) {
			printErrorAndExit(ERROR_ARGUMENT_COUNT);
		}
	}

	private void exitIfInvalidFileName(String[] args) {
		String fileName = args[0];
		exitIfIncorrectExtension(fileName);
		exitIfIncorrectLength(fileName);
	}

	private void exitIfIncorrectExtension(String fileName) {
		boolean endsWithCorrectExtension = fileName.endsWith(VALID_EXTENSION);
		if (!endsWithCorrectExtension) {
			printErrorAndExit(ERROR_EXTENSION);
		}
	}

	private void exitIfIncorrectLength(String fileName) {
		String fileNameWithoutExtension = fileName.replaceAll(VALID_EXTENSION,"");
		boolean isLengthZero = (fileNameWithoutExtension.trim().length()==0);
		if (isLengthZero) {
			printErrorAndExit(ERROR_FILE_NAME_LENGTH);
		}
	}

	private void exitIfFilesystemError() {
		exitIfFileNotReadableWritable();
	}
	
	private void exitIfFileNotReadableWritable() {
		boolean isFileReadableWritable = logic.isFileReadableWritable();
		if (!isFileReadableWritable) {
			printErrorAndExit(ERROR_FILE_RW);
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

	private void systemExit() {
		System.exit(0);
	}

	// Input Methods

	private void discardLine() {
		scanner.nextLine();
	}

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
			return INVALID_INTEGER;
		}
	}

	// Output Methods

	private void printErrorAndExit(String errorMessage) {
		systemPrint(errorMessage);
		System.exit(1);
	}

	private void printWelcomeMessage() {
		System.out.printf(FORMAT_WELCOME, logic.getFileName());
	}

	private void printFileReadyError() {
		System.out.println(ERROR_FILE_RW);
	}

	private void systemPrint(String feedback) {
		System.out.println(feedback);
	}

	private String listToString(List<String> list) {
		String consolidateString = "";
		int lineNumber = 1;
		for (String content : list) {
			consolidateString += String.format(FORMAT_DISPLAY, lineNumber,
					content);
			lineNumber++;
		}
		String trimmedString = consolidateString.trim();
		return trimmedString;
	}
}
