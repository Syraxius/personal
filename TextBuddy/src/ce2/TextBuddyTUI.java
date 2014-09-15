package ce2;

import java.util.List;
import java.util.Scanner;

/**
 * This program is implements the Text UI for the TextBuddy system.
 *
 * TextBuddyTUI handles user interaction and comprehensive input checking.
 * 
 * The program makes use of TextBuddyLogic to handle low level tasks.
 * 
 * It has been updated to support alternate shorthand commands using String dictionaries.
 * (Just an array of String for the time being, not the Dictionary data structure).
 *
 * Methods are sorted according to abstraction level from the highest to the lowest level.
 *
 * @author Ang Kah Min, Kelvin
 */
public class TextBuddyTUI {

	// Command Type Enumeration

	private static enum CommandType {
		ADD, DELETE, DISPLAY, CLEAR, SORT, SEARCH, EXIT, INVALID
	};

	// Dictionaries

	private static final String[] DICTIONARY_ADD = { "add", "-a", "/a" };
	private static final String[] DICTIONARY_CLEAR = { "clear", "-c", "/c" };
	private static final String[] DICTIONARY_DELETE = { "delete", "-de", "/de" };
	private static final String[] DICTIONARY_DISPLAY = { "display", "-di", "/di" };
	private static final String[] DICTIONARY_SORT = { "sort", "-s", "/s" };
	private static final String[] DICTIONARY_SEARCH = { "search", "-se", "/se" };
	private static final String[] DICTIONARY_EXIT = { "exit", "-e", "/e" };
	private static final String[] DICTIONARY_NTFS_INVALID = { "<", ">", ":",
															  "\"", "/", "\\",
															  "|", "?", "*" };

	// Error Strings

	private static final String ERROR_ARGUMENT_COUNT = "Wrong argument count supplied.";
	private static final String ERROR_EXTENSION = "Invalid file extension.";
	private static final String ERROR_FILE_NAME_LENGTH = "File name should be at least 1 character long.";
	private static final String ERROR_FILE_RW = "There has been a filesystem error.";
	private static final String ERROR_INVALID_CHARACTERS = "File name contains invalid characters.";

	private static final String ERROR_ADD = "Add command has failed.";
	private static final String ERROR_CLEAR = "Clear command has failed.";
	private static final String ERROR_DELETE = "Delete command failed.";
	private static final String ERROR_FATAL = "A fatal error has been encountered.";
	private static final String ERROR_INVALID_COMMAND = "Command is invalid.";

	// Error Codes

	private static final int INVALID_INTEGER = -1;

	// Printed Strings

	private static final String FORMAT_ADD = "added to %1$s: \"%2$s\"";
	private static final String FORMAT_CLEAR = "all content deleted from %1$s";
	private static final String FORMAT_DELETE = "deleted from %1$s: \"%2$s\"";
	private static final String FORMAT_DISPLAY = "%1$d. %2$s\n";
	private static final String FORMAT_EMPTY = "%1$s is empty";
	private static final String FORMAT_SORT = "%1$s has been sorted";
	private static final String FORMAT_SEARCH = "%2$s\n";
	private static final String FORMAT_PROMPT = "command: ";
	private static final String FORMAT_WELCOME = "Welcome to TextBuddy. %1$s is ready for use\n";

	// Other Constants

	private static final String VALID_EXTENSION = ".txt";

	// Objects Declaration

	TextBuddyLogic logic;
	Scanner scanner;

	// High Level System Implementation

	public TextBuddyTUI(String[] args) {
		systemAssertArguments(args);
		systemInitialize(args);
		systemAssertReady();
	}

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
	}

	private void interactiveUntilUserExits() {
		while (true) {
			String userCommand = getUserCommand();
			String feedback = processUserCommand(userCommand);
			systemSave();
			showToUser(feedback);
		}
	}

	// Basic Operations

	public String processUserCommand(String userCommand) {
		String firstWord = getFirstWord(userCommand);
		CommandType commandType = getCommandType(firstWord);
		switch (commandType) {
		case ADD :
			return addLine(userCommand);
		case CLEAR :
			return clearAllLines();
		case DELETE :
			return deleteLine(userCommand);
		case DISPLAY :
			return displayAllLines();
		case SORT :
			return sortByName();
		case SEARCH :
			return searchByKeyword(userCommand);
		case EXIT :
			exit();
		case INVALID :
			return ERROR_INVALID_COMMAND;
		default :
			throw new Error(ERROR_FATAL);
		}
	}

	private CommandType getCommandType(String command) {
		String commandLowerCase = command.toLowerCase();
		if (isDictionaryContains(DICTIONARY_ADD, commandLowerCase)) {
			return CommandType.ADD;
		} else if (isDictionaryContains(DICTIONARY_CLEAR, commandLowerCase)) {
			return CommandType.CLEAR;
		} else if (isDictionaryContains(DICTIONARY_DELETE, commandLowerCase)) {
			return CommandType.DELETE;
		} else if (isDictionaryContains(DICTIONARY_DISPLAY, commandLowerCase)) {
			return CommandType.DISPLAY;
		} else if (isDictionaryContains(DICTIONARY_SORT, commandLowerCase)) {
			return CommandType.SORT;
		} else if (isDictionaryContains(DICTIONARY_SEARCH, commandLowerCase)) {
			return CommandType.SEARCH;
		} else if (isDictionaryContains(DICTIONARY_EXIT, commandLowerCase)) {
			return CommandType.EXIT;
		} else {
			return CommandType.INVALID;
		}
	}

	private String addLine(String userCommand) {
		String lineToAdd = removeFirstWord(userCommand);

		boolean isAddSuccess = logic.addLine(lineToAdd);
		if (isAddSuccess) {
			return String.format(FORMAT_ADD, logic.getFileName(), lineToAdd);
		} else {
			return ERROR_ADD;
		}
	}

	private String deleteLine(String userCommand) {
		String lineNumberString = removeFirstWord(userCommand);
		int lineNumber = parseInt(lineNumberString);
		int actualLineNumber = lineNumber - 1;
		String deletedLine = logic.deleteLine(actualLineNumber);

		boolean isDeleteSuccess = deletedLine != null;
		if (isDeleteSuccess) {
			return String.format(FORMAT_DELETE, logic.getFileName(),
					deletedLine);
		} else {
			return ERROR_DELETE;
		}
	}

	private String displayAllLines() {
		List<String> list = logic.getList();

		boolean isListEmpty = list.isEmpty();
		if (isListEmpty) {
			return String.format(FORMAT_EMPTY, logic.getFileName());
		} else {
			return listToNumberedString(list);
		}
	}

	private String clearAllLines() {
		boolean isClearSuccess = logic.clearAllLines();
		if (isClearSuccess) {
			return String.format(FORMAT_CLEAR, logic.getFileName());
		} else {
			return ERROR_CLEAR;
		}
	}
	
	private String sortByName() {
		return null;
	}
	
	private String searchByKeyword(String keyword) {
		return null;
	}

	private void exit() {
		systemCleanUp();
		systemExit();
	}

	// Input Methods

	private String getUserCommand() {
		System.out.print(FORMAT_PROMPT);
		String userCommand = scanner.nextLine();
		return userCommand;
	}

	private String getFirstWord(String userCommand) {
		String oneOrMoreSpaces = "\\s+";
		String[] splitUserCommand = userCommand.split(oneOrMoreSpaces);
		String firstWord = splitUserCommand[0];
		return firstWord;
	}

	private String removeFirstWord(String userCommand) {
		String blank = "";
		String firstWord = getFirstWord(userCommand);
		String removedFirstWord = userCommand.replaceFirst(firstWord, blank);
		String removedFirstWordTrimmed = removedFirstWord.trim();
		return removedFirstWordTrimmed;
	}

	private int parseInt(String intString) {
		try {
			int value = Integer.parseInt(intString);
			return value;
		} catch (Exception e) {
			return INVALID_INTEGER;
		}
	}

	private boolean isDictionaryContains(String[] dictionary, String command) {
		boolean isFound = false;
		for (int i = 0; i < dictionary.length; i++) {
			if (dictionary[i].equals(command)) {
				isFound = true;
				break;
			}
		}
		return isFound;
	}

	// Output Methods

	private void printErrorAndExit(String errorMessage) {
		showToUser(errorMessage);
		System.exit(1);
	}

	private void printWelcomeMessage() {
		System.out.printf(FORMAT_WELCOME, logic.getFileName());
	}

	private void showToUser(String feedback) {
		System.out.println(feedback);
	}

	private String listToNumberedString(List<String> list) {
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

	// Initialization and Clean Up

	private void initializeLogic(String[] args) {
		logic = new TextBuddyLogic(args[0]);
	}

	private void initializeTUI() {
		scanner = new Scanner(System.in);
	}

	private void systemSave() {
		logic.saveToFile();
	}

	private void systemCleanUp() {
		scanner.close();
	}

	private void systemExit() {
		System.exit(0);
	}
	
	// System Assertions

	private void exitIfInvalidFileName(String[] args) {
		String fileName = args[0];
		exitIfIncorrectExtension(fileName);
		exitIfIncorrectLength(fileName);
		exitIfContainsInvalidCharacters(fileName);
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

	private void exitIfInvalidArgumentCount(String[] args) {
		boolean isOnlyOneArgument = (args.length == 1);
		if (!isOnlyOneArgument) {
			printErrorAndExit(ERROR_ARGUMENT_COUNT);
		}
	}

	private void exitIfIncorrectExtension(String fileName) {
		boolean isCorrectExtension = fileName.endsWith(VALID_EXTENSION);
		if (!isCorrectExtension) {
			printErrorAndExit(ERROR_EXTENSION);
		}
	}

	private void exitIfIncorrectLength(String fileName) {
		String fileNameWithoutExtension = fileName.replaceAll(VALID_EXTENSION,
															  "");
		boolean isLengthZero = (fileNameWithoutExtension.trim().length() == 0);
		if (isLengthZero) {
			printErrorAndExit(ERROR_FILE_NAME_LENGTH);
		}
	}

	private void exitIfContainsInvalidCharacters(String fileName) {
		for (int i = 0; i < fileName.length(); i++) {
			String currentCharacter = fileName.substring(i, i + 1);
			boolean isInvalidCharacter = isDictionaryContains(
					DICTIONARY_NTFS_INVALID, currentCharacter);
			if (isInvalidCharacter) {
				printErrorAndExit(ERROR_INVALID_CHARACTERS);
			}
		}
	}
}