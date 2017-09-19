package cop701.common;

public class Square {

	Color owner;
	Integer numPiece;
	Boolean isStar;
	
	public Square() {
		this.owner = null;
		this.numPiece = 0;
		this.isStar = false; 
	}

	public Color getOwner() {
		return owner;
	}

	public void setOwner(Color owner) {
		this.owner = owner;
	}

	public Integer getNumPiece() {
		return numPiece;
	}

	public void setNumPiece(Integer numPiece) {
		this.numPiece = numPiece;
	}

	public Boolean getIsStar() {
		return isStar;
	}

	public void setIsStar(Boolean isStar) {
		this.isStar = isStar;
	}
	
}
