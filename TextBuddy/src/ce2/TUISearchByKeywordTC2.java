package ce2;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TUISearchByKeywordTC2 {
	
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
		tui.processUserCommand("add applepie");
		tui.processUserCommand("add apple");
		assertEquals("- applepie\n- apple",tui.processUserCommand("search apple"));
		tui.processUserCommand("clear");
	}

}
