package ce2;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class sortByNameTC1 {
	
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
		boolean sortSuccess = logic.sort();
		assertTrue(sortSuccess);
	}

}
