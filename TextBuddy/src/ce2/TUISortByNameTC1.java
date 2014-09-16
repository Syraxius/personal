package ce2;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This tests whether sortByName() shows the correct message.
 *
 * @author Ang Kah Min, Kelvin
 */
public class TuiSortByNameTC1 {
	
	TextBuddyTui tui;

	@Before
	public void setUp() throws Exception {
		String[] args = {"test.txt"};
		tui = new TextBuddyTui(args);
		tui.processUserCommand("add c");
		tui.processUserCommand("add b");
		tui.processUserCommand("add a");
	}

	@After
	public void tearDown() throws Exception {
		tui.processUserCommand("clear");
	}

	@Test
	public void test() {
		assertEquals("test.txt has been sorted", tui.processUserCommand("sort"));
	}

}
