package cop701.bot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cop701.common.GameState;
import cop701.common.Move;

public class RandomAI extends AbstractAI {

	@Override
	public List<Move> getMoveList(GameState gameState, List<Integer> diceThrow) {
		GameState dummyGameState = new GameState(gameState);
		List<Move> moves = new ArrayList<Move>();
		List<Integer> pieceIds = Arrays.asList(0,1,2,3);
		for(int i=0;i<diceThrow.size();++i) {
			Collections.shuffle(pieceIds);
			for(int j=0;j<4;++j) {	
				int curPiece = pieceIds.get(j);
				Move move = new Move(curPiece, diceThrow.get(i));
				boolean isValid = dummyGameState.validateMove(0,move);
				if(isValid)	{
					moves.add(move);
					dummyGameState.updatePiece(0,move);
					break;
				}
			}
		}
		if(moves.size() == 0)
			moves.add(new Move(0,0));
		
		return moves;
	}
	
	

}
