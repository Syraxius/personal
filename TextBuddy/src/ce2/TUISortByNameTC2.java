package ce2;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This tests whether sortByName() sorts correctly.
 *
 * @author Ang Kah Min, Kelvin
 */
public class TUISortByNameTC2 {
	
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
		tui.processUserCommand("add c");
		tui.processUserCommand("add b");
		tui.processUserCommand("add a");
		tui.processUserCommand("sort");
		assertEquals("1. a\n2. b\n3. c",tui.processUserCommand("display"));
		tui.processUserCommand("clear");
	}

}
