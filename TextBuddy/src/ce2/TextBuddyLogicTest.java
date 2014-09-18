package ce2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
		logic.clearAllLines();
	}

	//Search through empty list.
	@Test
	public void searchByKeywordTc1() {
		List<String> list = logic.searchByKeyword("applepie");
		assertEquals(0,list.size());
	}
	
	//Search for null.
	@Test
	public void searchByKeywordTc2() {
		List<String> list = logic.searchByKeyword(null);
		assertEquals(0,list.size());
	}
	
	//Search for exact keyword.
	@Test
	public void searchByKeywordTc3() {
		logic.addLine("applepie");
		List<String> list = logic.searchByKeyword("applepie");
		assertEquals("applepie",list.get(0));
	}
	
	//Search for partial keyword.
	@Test
	public void searchByKeywordTc4() {
		logic.addLine("applepie");
		List<String> list = logic.searchByKeyword("apple");
		assertEquals("applepie",list.get(0));
	}
	
	//Search for 2 or more matching items.
	@Test
	public void searchByKeywordTc5() {
		logic.addLine("applepie");
		logic.addLine("applebanana");
		logic.addLine("bananapie");
		List<String> list = logic.searchByKeyword("banana");
		assertEquals("applebanana",list.get(0));
		assertEquals("bananapie",list.get(1));
	}
	
	//Search case-insensitive.
	@Test
	public void searchByKeywordTc6() {
		logic.addLine("applepie");
		logic.addLine("applebanana");
		logic.addLine("bananapie");
		List<String> list = logic.searchByKeyword("Banana");
		assertEquals("applebanana",list.get(0));
		assertEquals("bananapie",list.get(1));
	}
	
	//Test if sorting returns success on an empty list.
	@Test
	public void sortByNameTc1() {
		boolean sortSuccess = logic.sortByName();
		assertTrue(sortSuccess);
	}

	//Test if sorting returns success on a populated list.
	@Test
	public void sortByNameTc2() {
		logic.addLine("c");
		logic.addLine("b");
		logic.addLine("a");
		boolean sortSuccess = logic.sortByName();
		assertTrue(sortSuccess);
	}

	//Test if able to sort 1 item.
	@Test
	public void sortByNameTc3() {
		logic.addLine("a");
		logic.sortByName();
		List<String> list = logic.getList();
		assertEquals("a", list.get(0));
	}
	
	//Test if able to sort 2 or more items.
	@Test
	public void sortByNameTc4() {
		logic.addLine("c");
		logic.addLine("b");
		logic.addLine("a");
		logic.sortByName();
		List<String> list = logic.getList();
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		assertEquals("c", list.get(2));
	}
	
	
}
