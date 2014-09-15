package ce2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * This program handles the logic for the TextBuddy system.
 * 
 * This implementation uses an ArrayList<String> as the data structure and is
 * able to perform add, delete, clear and save functionalities.
 * 
 * The text file is only used as data storage and is written to disk by
 * invoking the save() method.
 * 
 * @author Ang Kah Min, Kelvin
 */
public class TextBuddyLogic {

	// Warning Strings
	
	private static final String WARNING_READ = "Warning: Unable to read from file.";
	private static final String WARNING_WRITE = "Warning: Unable to create new file.";
	
	// Objects Declaration
	
	Path path;
	List<String> list;

	// Constructors

	public TextBuddyLogic(String fileName) {
		initializePath(fileName);
		initializeFile();
		initializeList();
	}

	// Initialization Methods

	private void initializePath(String fileName) {
		this.path = Paths.get(fileName);
	}

	private void initializeFile() {
		boolean isExistingFile = Files.exists(path);
		if (!isExistingFile) {
			try {
				// This would write a blank file if it does not exist.
				Files.write(path, new byte[0], StandardOpenOption.CREATE,
						StandardOpenOption.TRUNCATE_EXISTING);
			} catch (IOException e) {
				System.out.println(WARNING_WRITE);
			}
		}
	}

	private void initializeList() {
		try {
			list = Files.readAllLines(path);
		} catch (Exception e) {
			System.out.println(WARNING_READ);
		}
	}

	// Accessor Methods

	public String getFilePath() {
		return this.path.toString();
	}

	public String getFileName() {
		return this.path.getFileName().toString();
	}

	public List<String> getList() {
		return this.list;
	}

	public int getListSize() {
		return this.list.size();
	}

	// Verification Methods

	public boolean isFileReadableWritable() {
		return Files.isReadable(path) && Files.isWritable(path);
	}

	// Operation Methods

	public boolean addLine(String content) {
		boolean success;
		try {
			boolean isNull = content == null;
			boolean isZeroLength = content.length() == 0;
			if (isNull || isZeroLength) {
				throw new NullPointerException();
			}
			list.add(content);
			success = true;
		} catch (Exception e) {
			success = false;
		}
		return success;
	}

	public String deleteLine(int lineNumber) {
		String deletedLine;
		try {
			deletedLine = list.remove(lineNumber);
		} catch (Exception e) {
			deletedLine = null;
		}
		return deletedLine;
	}

	public boolean clearAllLines() {
		boolean success;
		try {
			list.clear();
			success = true;
		} catch (Exception e) {
			success = false;
		}
		return success;
	}

	public boolean saveToFile() {
		boolean success;
		try {
			Files.write(path, list, StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING);
			success = true;
		} catch (Exception e) {
			success = false;
		}
		return success;
	}
	
	public boolean sort() {
		return false;
	}
	
	public List<String> searchByKeyword(String keyword) {
		return null;
	}
}