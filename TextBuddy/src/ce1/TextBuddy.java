package ce1;

/**
 * This program instantiates and run TextBuddy Text UI.
 *
 * The program requires a valid NTFS file name to be
 * supplied as the argument.
 *
 * @author Ang Kah Min, Kelvin
 */
public class TextBuddy {

	public static void main(String[] args) {
		TextBuddyTUI tb = new TextBuddyTUI(args);
		tb.systemRun();
	}
}
