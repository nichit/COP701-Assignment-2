package cop701.bot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cop701.common.GameState;
import cop701.common.Move;

public class HunterAI extends AbstractAI {

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
		System.err.println("Score: " + max.val);
		
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

	@Override
	protected int calculateScore(GameState gameState, int extraRoll) {
		int score = 0;

		//for extra roll we get
		while(extraRoll-- != 0)
			score += 20;
		
		Integer pieces [] [] = gameState.getPieces();
		
		//our pieces evaluation
		for(int j=0; j<4; ++j) {
			//our piece at star
			if(pieces[0][j] != -1 && gameState.getBoard()[pieces[0][j]].getIsStar())
				score += 30;
			//our piece is on run
			if(pieces[0][j] != -1) {
				score += 60;
				for(int k=0; k<4; ++k) {
					int distance = calculateDistance(pieces[0][j],pieces[1][k]);
					//opponent piece behind our piece
					if(distance > 0 && distance <= 6)
						//score - 40 + steps required to reach goal
						score += -40 + (57 - pieces[0][j]); 
				
					//opponent piece ahead of our piece (capture time)
					if(distance >= -6 && distance < 0)
						if(gameState.getBoard()[pieces[1][k]].getIsStar())
							score += 10;
						else
							score += 40;
				}
			}	
		}
		
		for(int k=0; k<4; ++k)
			if(pieces[1][k] == -1)
				score += 1000;
		
		
		return score;
	}

}
