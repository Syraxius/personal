package ce2;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This tests whether searchByKeyword() is able to search for single items.
 *
 * @author Ang Kah Min, Kelvin
 */
public class TUISearchByKeywordTC1 {
	
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
		tui.processUserCommand("add carrot");
		tui.processUserCommand("add banana");
		tui.processUserCommand("add apple");
		assertEquals("- apple",tui.processUserCommand("search apple"));
		tui.processUserCommand("clear");
	}

}
