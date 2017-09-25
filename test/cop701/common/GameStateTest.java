package cop701.common;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class GameStateTest {

	@Test
	public void testGameStateInit() {
		GameState gameState = new GameState(new HashMap<Integer, Color>());
		Square[] sq = gameState.getBoard();
		int countStar = 0;
		for (int i=0; i<sq.length; i++) {
			if (sq[i].getIsStar()) countStar++;
		}
		assertEquals(28, countStar);
	}

}
