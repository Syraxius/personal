package ce2;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This tests whether sortByName() actually sorts the items.
 *
 * @author Ang Kah Min, Kelvin
 */
public class LogicSortByNameTc2 {

	TextBuddyLogic logic;

	@Before
	public void setUp() throws Exception {
		logic = new TextBuddyLogic("test.txt");
		logic.addLine("c");
		logic.addLine("b");
		logic.addLine("a");
	}

	@After
	public void tearDown() throws Exception {
		logic.clearAllLines();
	}

	@Test
	public void test() {
		logic.sortByName();
		List<String> list = logic.getList();
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		assertEquals("c", list.get(2));
	}

}
