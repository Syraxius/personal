package ce2;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This tests whether searchByKeyword() is able to inform of invalid search term.
 *
 * @author Ang Kah Min, Kelvin
 */
public class TUISearchByKeywordTC3 {
	
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
		assertEquals("\"apple\" not found in test.txt",tui.processUserCommand("search apple"));
		tui.processUserCommand("clear");
	}

}
