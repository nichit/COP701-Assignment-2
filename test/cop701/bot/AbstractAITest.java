package cop701.bot;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractAITest {

	@Test
	public void test() {
		RandomAI ai = new RandomAI();
		assertEquals(1000,ai.calculateDistance(-1,4));
		assertEquals(1000,ai.calculateDistance(157,4));
		assertEquals(1000,ai.calculateDistance(4,-1));
		assertEquals(1000,ai.calculateDistance(6,130));
		assertEquals(1000,ai.calculateDistance(51,4));
		assertEquals(1,ai.calculateDistance(5,4));
		assertEquals(-1,ai.calculateDistance(3,4));
		assertEquals(0,ai.calculateDistance(9,9));
		assertEquals(1000,ai.calculateDistance(26,25));
		assertEquals(-25,ai.calculateDistance(26,51));
		assertEquals(-2,ai.calculateDistance(25,27));
		assertEquals(3,ai.calculateDistance(51,48));
		assertEquals(1000,ai.calculateDistance(27,1));
		assertEquals(-26,ai.calculateDistance(1,27));
		assertEquals(2,ai.calculateDistance(1,51));
	}

}
