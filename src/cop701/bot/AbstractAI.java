package cop701.bot;

import cop701.common.GameState;

public abstract class AbstractAI implements AI {
	
	protected int calculateScore(GameState gameState, int extraRoll) {
		return 0;
	}
	
	protected int calculateDistance(Integer ourPieceLoc, Integer oppPieceLoc) {
		if(ourPieceLoc == -1 || ourPieceLoc > 100)
			return 1000;//assuming infinity
		if(oppPieceLoc == -1 || oppPieceLoc > 100)
			return 1000;
		// we cannot chase each other
		if(ourPieceLoc >= 26 && oppPieceLoc <= 25)
			return 1000;
		if(ourPieceLoc <=12 && oppPieceLoc >= 39)
			return ourPieceLoc + (52 - oppPieceLoc);
		else
			return ourPieceLoc - oppPieceLoc;
	}
}
