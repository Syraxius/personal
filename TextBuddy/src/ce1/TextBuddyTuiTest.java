package ce1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This consists of test cases for TextBuddyTui class.
 *
 * @author Ang Kah Min, Kelvin
 */
public class TextBuddyTuiTest {

	TextBuddyTui tui;
	
	@Before
	public void setUp() throws Exception {
		String[] args = {"test.txt"};
		tui = new TextBuddyTui(args);
	}

	@After
	public void tearDown() throws Exception {
		tui.processUserCommand("clear");
	}

	@Test
	public void dictionaryTc1() {
		assertEquals("Command is invalid.",tui.processUserCommand("a hello"));
	}

	@Test
	public void dictionaryTc2() {
		assertEquals("added to test.txt: \"hello\"",tui.processUserCommand("add hello"));
		assertEquals("added to test.txt: \"hello\"",tui.processUserCommand("-a hello"));
		assertEquals("added to test.txt: \"hello\"",tui.processUserCommand("/a hello"));
	}

}
