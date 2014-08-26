package ce1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class TextBuddyLogic {

	// Objects Declaration

	Path path;
	List<String> list;

	// Constructors

	public TextBuddyLogic(String filePath) {
		initializePath(filePath);
		initializeFile();
		initializeList();
	}

	// Initialization Methods

	private void initializePath(String filePath) {
		this.path = Paths.get(filePath);
	}

	private void initializeFile() {
		if (!Files.exists(path)) {
			try {
				Files.write(path, new byte[0], StandardOpenOption.CREATE,
						StandardOpenOption.TRUNCATE_EXISTING);
			} catch (IOException e) {
			}
		}
	}

	private void initializeList() {
		try {
			list = Files.readAllLines(path);
		} catch (Exception e) {
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

	// Verification Methods

	public boolean isFileReadableWritable() {
		return Files.isReadable(path) && Files.isWritable(path);
	}

	// Operation Methods

	public boolean add(String content) {
		boolean success;
		try {
			list.add(content);
			success = true;
		} catch (Exception e) {
			success = false;
		}
		return success;
	}

	public String delete(int lineNumber) {
		String deletedLine;
		try {
			deletedLine = list.remove(lineNumber - 1);
		} catch (IndexOutOfBoundsException e) {
			deletedLine = null;
		}
		return deletedLine;
	}

	public boolean clear() {
		boolean success;
		try {
			list.clear();
			success = true;
		} catch (Exception e) {
			success = false;
		}
		return success;
	}

	public boolean save() {
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
}
