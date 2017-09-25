package cop701.common;

public class Square {

	private Boolean isStar;
	private int[] noOfPieces = new int[2];//(R,Y) or (B,G)
	
	public Square() {	
		this.isStar = false;
	}
	
	public Boolean getIsStar() {
		return isStar;
	}

	public void setIsStar(Boolean isStar) {
		this.isStar = isStar;
	}

	public int[] getNoOfPieces() {
		return noOfPieces;
	}

	public void setNoOfPieces(int[] noOfPieces) {
		this.noOfPieces = noOfPieces;
	}
	
	public void updatePieces(int player, int pieceCount) {
		noOfPieces[player] = pieceCount;
	}
	
	public void addPieces(int player, int pieceDelta) {
		noOfPieces[player] += pieceDelta;
	}
	
}
