package cop701.common;

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
	
	private Integer pieces[][] = new Integer[2][4];
	
	private Square board[] = new Square[200];
	
	public GameState() {
		initGameBoard();
	}

	private void initGameBoard() {
		// TODO Auto-generated method stub
		
	}
	
	public void updatePiece(Integer player, Integer pieceId, Integer stepsToMove) {
		// TODO to be implemented
	}
	
	public Integer[][] getPieces() {
		return pieces;
	}

	public Square[] getBoard() {
		return board;
	}
	
}
