package ce1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This tests whether clear() can clear appropriately.
 *
 * @author Ang Kah Min, Kelvin
 */
public class LogicClearTC1 {

	TextBuddyLogic logic = new TextBuddyLogic("test.txt");
	
	@Before
	public void setUp() throws Exception {
		logic.clear();
		logic.add("a");
		logic.add("b");
	}

	@After
	public void tearDown() throws Exception {
		logic.clear();
	}

	@Test
	public void test() {
		assertEquals("a",logic.getList().get(0));
		assertEquals("b",logic.getList().get(1));
		assertTrue(logic.clear());
		assertEquals(null,logic.delete(0));
		assertTrue(logic.clear());
	}

}