package cop701.bot;

import java.util.List;

import cop701.common.GameState;
import cop701.common.Move;

public interface AI {

	/**
	 * Given game state and dice throw, this should return the move list
	 * Note that this shouldn't update the game state
	 * 
	 * @param gameState
	 * @param diceThrow
	 * @return
	 */
	List<Move> getMoveList(GameState gameState, List<Integer> diceThrow);
	
}
