package cop701.main;

import static org.junit.Assert.*;

import org.junit.Test;

public class LudoTest {

	@Test
	public void test() {
		String input = "R0_1<next>R1_6<next>R0_4";
		String[] tokens = input.split("<next>");
		
		for (int i=0; i<tokens.length; i++) {
			System.out.println(tokens[i]);
		}
		assertEquals(3, tokens.length);
	}

}
