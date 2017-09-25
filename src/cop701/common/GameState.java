package cop701.common;

import java.util.HashMap;
import java.util.Map;

public class GameState {

	/**
	 * Game board is represented is this manner
	 * 
	 * One arm:
	 * Not started = -1
	 * 0   1   2   3   4   5
	 * 51  152 153 154 155 156 Win = 999
	 * 50  49  48  47  46  45
	 */
	private Map<Integer,Color> colorMap = new HashMap<Integer,Color>();
	
	private Integer pieces[][] = new Integer[2][4];
	
	private Square board[] = new Square[200];
	
	public GameState() {
		initGameBoard();
	}

	private void initGameBoard() {
		int i,j;
		for(i=0;i<=51;++i) {
			if(i%13==1 || i%13==9)
				board[i].setIsStar(true); 
		}
		
		//Walk Of Fame Steps
		for(i=0;i<=3;i++) {
			for(j=0;j<=4;++j) {
				board[113+13*i+j].setIsStar(true);
			}
		}
	}
	

	public void updatePiece(Integer player, Move move) {
		//Update the state of two squares
		if(move.getSteps() != 0) {
			
			if(pieces[player][move.getPieceId()] == -1) {
				pieces[player][move.getPieceId()] = player*26 + 1;
				board[player*26 + 1].getNoOfPieces()[player]++;
			}
			
			int nextSquareNo = pieces[player][move.getPieceId()] + move.getSteps();
			
			if(player == 0) {
				if(nextSquareNo > 51 && nextSquareNo < 152) {
					nextSquareNo = 100 + nextSquareNo;	
				}
			}
			else {
				if(nextSquareNo > 25 && nextSquareNo < 126) {
					nextSquareNo = 100 + nextSquareNo;	
				}
			}
			
			board[pieces[player][move.getPieceId()]].getNoOfPieces()[player]--;
			board[nextSquareNo].getNoOfPieces()[player]++;
			pieces[player][move.getPieceId()] = nextSquareNo;
		}
	}
	
	public boolean validateMove(Integer player, Move move) {
		if(move.getSteps() == 0)
			return true;
		if(pieces[player][move.getPieceId()] == -1)
			if(move.getSteps() != 1)	return false;
			else					return true;
		
		int nextSquareNo = pieces[player][move.getPieceId()] + move.getSteps();
		
		if(player == 0) {
			if(nextSquareNo > 51 && nextSquareNo < 152) {
				nextSquareNo = 100 + nextSquareNo;	
			}
			else if(nextSquareNo > 157)
				return false;
		}
		else
			if(nextSquareNo > 25 && nextSquareNo < 126) {
				nextSquareNo = 100 + nextSquareNo;	
			}
			else if(nextSquareNo > 131)
				return false;

		if(!board[nextSquareNo].getIsStar() && board[nextSquareNo].getNoOfPieces()[player]>0) {
				return false;
		}
				 
		return true;

	}
	
	public Integer[][] getPieces() {
		return pieces;
	}

	public Square[] getBoard() {
		return board;
	}
	
}
