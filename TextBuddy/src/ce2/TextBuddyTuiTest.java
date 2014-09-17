package ce2;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This consists of test cases for TextBuddyTui class.
 *
 * @author Ang Kah Min, Kelvin
 */
public class TextBuddyTuiTest {
	
	TextBuddyTui tui;

	@Before
	public void setUp() throws Exception {
		String[] args = {"test.txt"};
		tui = new TextBuddyTui(args);
	}

	@After
	public void tearDown() throws Exception {
		tui.processUserCommand("clear");
	}

	//Search for non-existent keyword in empty list
	@Test
	public void searchByKeywordTc1() {
		assertEquals("\"apple\" not found in test.txt",tui.processUserCommand("search apple"));
	}
	
	//Search for non-existent keyword in populated list
	@Test
	public void searchByKeywordTc2() {
		tui.processUserCommand("add carrot");
		tui.processUserCommand("add banana");
		assertEquals("\"apple\" not found in test.txt",tui.processUserCommand("search apple"));
	}

	//Search for exact keyword with one matching item in list
	@Test
	public void searchByKeywordTc3() {
		tui.processUserCommand("add apple");
		assertEquals("- apple",tui.processUserCommand("search apple"));
	}

	//Search for exact keyword with two or more matching items in list
	@Test
	public void searchByKeywordTc4() {
		tui.processUserCommand("add apple");
		tui.processUserCommand("add applepie");
		tui.processUserCommand("add banana");
		assertEquals("- apple\n- applepie",tui.processUserCommand("search apple"));
	}
	
	//Test if correct message is shown by sort.
	@Test
	public void sortByNameTc1() {
		assertEquals("test.txt has been sorted", tui.processUserCommand("sort"));
	}
	
	//Sort an empty list.
	@Test
	public void sortByNameTc2() {
		tui.processUserCommand("sort");
		assertEquals("test.txt is empty", tui.processUserCommand("display"));
	}
	
	//Sort a list with 1 item.
	@Test
	public void sortByNameTc3() {
		tui.processUserCommand("add apple");
		tui.processUserCommand("sort");
		assertEquals("1. apple", tui.processUserCommand("display"));
	}
	
	//Sort a list with 2 or more items.
	@Test
	public void sortByNameTc4() {
		tui.processUserCommand("add banana");
		tui.processUserCommand("add apple");
		tui.processUserCommand("sort");
		assertEquals("1. apple\n2. banana", tui.processUserCommand("display"));
	}
}
