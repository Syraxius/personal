package ce1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This tests whether add() can add items appropriately.
 *
 * @author Ang Kah Min, Kelvin
 */
public class LogicAddTC1 {

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
		logic.add("a");
		assertEquals("a",logic.getList().get(0));
		logic.add("b");
		assertEquals("b",logic.getList().get(1));

		assertFalse(logic.add(""));
		assertFalse(logic.add(null));
	}

}
