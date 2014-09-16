package ce1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This tests whether add() does not add invalid items.
 *
 * @author Ang Kah Min, Kelvin
 */
public class LogicAddTC2 {

	TextBuddyLogic logic = new TextBuddyLogic("test.txt");
	
	@Before
	public void setUp() throws Exception {
		logic.clear();
	}

	@After
	public void tearDown() throws Exception {
		logic.clear();
	}

	@Test
	public void test() {
		assertFalse(logic.add(""));
		assertFalse(logic.add(null));
		assertFalse(logic.add(" "));
	}

}
