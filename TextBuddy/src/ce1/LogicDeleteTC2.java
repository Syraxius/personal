package ce1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This tests whether delete() can handle invalid inputs.
 *
 * @author Ang Kah Min, Kelvin
 */
public class LogicDeleteTc2 {

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
		assertEquals(null, logic.delete(2));
		assertEquals(null, logic.delete(-1));
		assertEquals("b",logic.delete(1));
		assertEquals("a",logic.delete(0));
		assertEquals(null,logic.delete(0));
	}

}
