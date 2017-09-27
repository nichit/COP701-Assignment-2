package cop701.bot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cop701.common.GameState;
import cop701.common.Move;

public class KillerAI implements AI {

	@Override
	public List<Move> getMoveList(GameState gameState, List<Integer> diceThrow) {
		List<Move> moves = new ArrayList<Move>();
		Wrapper max = new Wrapper();
		
		int n = diceThrow.size();
		for(int i=0;i<n;++i) {
			findOptimalMoves(0,diceThrow,gameState,moves,max,0);
			Collections.swap(diceThrow,0,n-i-1);
		}
		
		
		if(max.moves.size() == 0)
			max.moves.add(new Move(0,0));
		
		return max.moves;
	}


	private void findOptimalMoves(int i, List<Integer> diceThrow, GameState gameState, List<Move> moves, Wrapper max, int rolls) {
		
		int val = calculateScore(gameState,rolls);
		if(moves.size() > max.moves.size() || (val > max.val && moves.size() == max.moves.size())) {
			max.val = val;
			max.moves.clear();
			for(int j=0; j<moves.size(); ++j)
				max.moves.add(moves.get(j).copy()); 
		}
		
		if(i == diceThrow.size()) {
			//System.err.println(moves);
			return ;
		}
		for(int j=0; j<4; ++j) {
			Move move = new Move(j,diceThrow.get(i));
			boolean isValid = gameState.validateMove(0,move);
			if(isValid) {
				GameState dummyGameState = new GameState(gameState);
				int extraRolls = dummyGameState.updatePiece(0,move);
				moves.add(move);
				findOptimalMoves(i+1,diceThrow,dummyGameState,moves,max,rolls + extraRolls);
				moves.remove(i);
			}
		}
	}

	private int calculateScore(GameState gameState, int extraRoll) {
		Random rand = new Random();
		int n = rand.nextInt(50);
		return n;
	}

}
