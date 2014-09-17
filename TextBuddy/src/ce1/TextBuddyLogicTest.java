package ce1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This consists of test cases for TextBuddyLogic class.
 *
 * @author Ang Kah Min, Kelvin
 */
public class TextBuddyLogicTest {

	TextBuddyLogic logic;
	
	@Before
	public void setUp() throws Exception {
		logic = new TextBuddyLogic("test.txt");
	}

	@After
	public void tearDown() throws Exception {
		logic.clear();
		logic.save();
	}

	//Test for single add.
	@Test
	public void addTc1() {
		assertTrue(logic.add("a"));
		assertEquals("a",logic.getList().get(0));
	}
	
	//Test for multiple add.
	@Test
	public void addTc2() {
		assertTrue(logic.add("a"));
		assertEquals("a",logic.getList().get(0));
		assertTrue(logic.add("b"));
		assertEquals("b",logic.getList().get(1));
	}

	//Test for empty string add.
	@Test
	public void addTc3() {
		assertFalse(logic.add(""));
	}
	
	//Test for null add.
	@Test
	public void addTc4() {
		assertFalse(logic.add(null));
		assertFalse(logic.add(" "));
	}
	
	//Test for whitespace-only add.
	@Test
	public void addTc5() {
		assertFalse(logic.add(" "));
	}
	
	//Delete from empty list.
	@Test
	public void deleteTc1() {
		assertEquals(null, logic.delete(0));
	}
	
	//Delete from negative indices.
	@Test
	public void deleteTc2() {
		assertEquals(null, logic.delete(-1));
	}
	
	//Delete from non-existent indices.
	@Test
	public void deleteTc3() {
		logic.add("a");
		assertEquals(null, logic.delete(1));
	}
	
	//Delete two or more items.
	@Test
	public void deleteTc4() {
		logic.add("a");
		logic.add("b");
		assertEquals("a",logic.delete(0));
		assertEquals("b",logic.delete(0));
	}

	//Clear empty list.
	@Test
	public void clearTc1() {
		assertTrue(logic.clear());
		assertEquals(null,logic.delete(0));
	}
	
	//Clear one item list.
	@Test
	public void clearTc2() {
		logic.add("a");
		assertEquals("a",logic.getList().get(0));
		assertTrue(logic.clear());
		assertEquals(null,logic.delete(0));
	}
	
	//Clear two or more items list.
	@Test
	public void clearTc3() {
		logic.add("a");
		logic.add("b");
		assertEquals("a",logic.getList().get(0));
		assertEquals("b",logic.getList().get(1));
		assertTrue(logic.clear());
		assertEquals(null,logic.delete(0));
	}
	
	//Save nothing.
	@Test
	public void saveTc1() {
		assertTrue(logic.save());
		assertEquals(0,logic.getFileLineCount());
	}
	
	//Save 1 item.
	@Test
	public void saveTc2() {
		assertTrue(logic.add("a"));
		assertEquals(0,logic.getFileLineCount());
		assertTrue(logic.save());
		assertEquals(1,logic.getFileLineCount());
	}
	
	//Save 2 or more items.
	@Test
	public void saveTc3() {		
		assertTrue(logic.add("a"));
		assertTrue(logic.add("b"));
		assertEquals(0,logic.getFileLineCount());
		assertTrue(logic.save());
		assertEquals(2,logic.getFileLineCount());
	}
}
