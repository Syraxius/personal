package ce1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This tests whether command dictionary works properly.
 *
 * @author Ang Kah Min, Kelvin
 */
public class TuiDictionaryTC1 {

	TextBuddyTUI tui;
	
	@Before
	public void setUp() throws Exception {
		String[] args = {"test.txt"};
		tui = new TextBuddyTUI(args);
	}

	@After
	public void tearDown() throws Exception {
		tui.processUserCommand("clear");
	}

	@Test
	public void test() {
		assertEquals("added to test.txt: \"hello\"",tui.processUserCommand("add hello"));
		assertEquals("added to test.txt: \"hello\"",tui.processUserCommand("-a hello"));
		assertEquals("added to test.txt: \"hello\"",tui.processUserCommand("/a hello"));
		assertEquals("Command is invalid.",tui.processUserCommand("a hello"));
	}

}
