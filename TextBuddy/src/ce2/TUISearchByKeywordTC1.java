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
public class TuiSearchByKeywordTC1 {
	
	TextBuddyTui tui;

	@Before
	public void setUp() throws Exception {
		String[] args = {"test.txt"};
		tui = new TextBuddyTui(args);
		tui.processUserCommand("add carrot");
		tui.processUserCommand("add banana");
		tui.processUserCommand("add apple");
	}

	@After
	public void tearDown() throws Exception {
		tui.processUserCommand("clear");
	}

	@Test
	public void test() {
		assertEquals("- apple",tui.processUserCommand("search apple"));
	}

}
