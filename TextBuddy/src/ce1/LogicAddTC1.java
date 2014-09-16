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
public class LogicAddTc1 {

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
		assertTrue(logic.add("a"));
		assertEquals("a",logic.getList().get(0));
		assertTrue(logic.add("b"));
		assertEquals("b",logic.getList().get(1));
	}

}
