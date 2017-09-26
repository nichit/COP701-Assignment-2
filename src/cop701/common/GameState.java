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
	
	public GameState(Map<Integer,Color> colorMap) {
		this.colorMap = colorMap;
		initGameBoard();
		initPlayer();
	}

	public GameState(GameState obj) {
		for (int i=0; i<2; i++)
			for (int j=0; j<4; j++)
				this.pieces[i][j] = obj.pieces[i][j];
		for (int i=0; i<200; i++)
			this.board[i] = obj.board[i].copy();
	}
	
	private void initGameBoard() {
		int i,j;
		for (i=0; i<200; i++)
			board[i] = new Square();
		
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
	
	private void initPlayer() {
		for (int i=0; i<2; i++)
			for (int j=0; j<4; j++)
				pieces[i][j] = -1;
	}
	

	public void updatePiece(Integer player, Move move) {
		//Update the state of two squares
		if(move.getSteps() != 0) {
			
			// Starting a piece
			if(pieces[player][move.getPieceId()] == -1) {
				pieces[player][move.getPieceId()] = player*26 + 1;
				board[player*26 + 1].addPieces(player, 1);
				return;
			}
			
			int nextSquareNo = pieces[player][move.getPieceId()] + move.getSteps();
			
			// Going into home column
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
			
			// We step on opponents piece! Yeah!
			if (!board[nextSquareNo].getIsStar() && board[nextSquareNo].getNoOfPieces()[1-player] > 0) {
				board[nextSquareNo].updatePieces(1-player, 0);
				for (int i=0; i<4; i++)
					if (pieces[1-player][i] == nextSquareNo) {
						pieces[1-player][i] = -1;
					}
			}
			
			// Now we move our piece
			board[pieces[player][move.getPieceId()]].updatePieces(player, -1);
			board[nextSquareNo].updatePieces(player, 1);
			pieces[player][move.getPieceId()] = nextSquareNo;
		}
	}
	
	public boolean validateMove(Integer player, Move move) {
		if(move.getSteps() == 0)
			return true;
		if(pieces[player][move.getPieceId()] == -1)
			if(move.getSteps() == 1 || move.getSteps() == 6)	return true;
			else	return false;
		
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
	
	public Map<Integer, Color> getColorMap() {
		return colorMap;
	}
	
	public Integer[][] getPieces() {
		return pieces;
	}

	public Square[] getBoard() {
		return board;
	}
	
}
