package ce2;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortByNameTC2 {

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
		logic.sort();
		List<String> list = logic.getList();
		String line1 = list.get(0);
		String line2 = list.get(1);
		String line3 = list.get(2);
		assertEquals("a", line1);
		assertEquals("b", line2);
		assertEquals("c", line3);
	}

}
