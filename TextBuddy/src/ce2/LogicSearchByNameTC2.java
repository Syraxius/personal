package ce2;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LogicSearchByNameTC2 {
	
	TextBuddyLogic logic;

	@Before
	public void setUp() throws Exception {
		logic = new TextBuddyLogic("test.txt");
		logic.addLine("applepie");
		logic.addLine("applebanana");
		logic.addLine("bananapie");
		logic.addLine("bananana");
	}

	@After
	public void tearDown() throws Exception {
		logic.clearAllLines();
	}

	@Test
	public void test() {
		List<String> list = logic.searchByKeyword("banana");
		assertEquals("applebanana",list.get(0));
		assertEquals("bananapie",list.get(1));
		assertEquals("bananana",list.get(2));
	}

}
