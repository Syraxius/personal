package ce1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This tests whether save() saves appropriately.
 *
 * @author Ang Kah Min, Kelvin
 */
public class LogicSaveTc1 {

	TextBuddyLogic logic = new TextBuddyLogic("test.txt");
	
	@Before
	public void setUp() throws Exception {
		logic.clear();
	}

	@After
	public void tearDown() throws Exception {
		logic.clear();
		logic.save();
	}

	@Test
	public void test() {
		assertTrue(logic.save());
		assertEquals(0,logic.getFileLineCount());
		
		assertTrue(logic.add("a"));
		assertEquals(0,logic.getFileLineCount());
		assertTrue(logic.save());
		assertEquals(1,logic.getFileLineCount());
		
		assertTrue(logic.add("b"));
		assertEquals(1,logic.getFileLineCount());
		assertTrue(logic.save());
		assertEquals(2,logic.getFileLineCount());
	}

}
