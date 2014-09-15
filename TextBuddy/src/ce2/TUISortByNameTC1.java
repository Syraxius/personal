package ce2;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This tests whether sortByName() shows the correct messages.
 *
 * @author Ang Kah Min, Kelvin
 */
public class TUISortByNameTC1 {
	
	TextBuddyTUI tui;

	@Before
	public void setUp() throws Exception {
		String[] args = {"test.txt"};
		tui = new TextBuddyTUI(args);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertEquals("added to test.txt: \"c\"", tui.processUserCommand("add c"));
		assertEquals("added to test.txt: \"b\"", tui.processUserCommand("add b"));
		assertEquals("added to test.txt: \"a\"", tui.processUserCommand("add a"));
		assertEquals("test.txt has been sorted", tui.processUserCommand("sort"));
		assertEquals("all content deleted from test.txt", tui.processUserCommand("clear"));
	}

}
