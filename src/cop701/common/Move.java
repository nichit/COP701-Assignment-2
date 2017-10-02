package cop701.common;

public class Move {

	private int pieceId;
	private int steps; // steps == 0 is a NA move
	
	public Move(int pieceId, int steps) {
		this.pieceId = pieceId;
		this.steps = steps;
	}
	
	public Move(String move) {
		this.pieceId = Character.getNumericValue(move.charAt(1));
		String[] stepsStr = move.split("_");
		this.steps = Integer.valueOf(stepsStr[1]);
	}
	
	public int getPieceId() {
		return pieceId;
	}
	
	public void setPieceId(int pieceId) {
		this.pieceId = pieceId;
	}
	
	public int getSteps() {
		return steps;
	}
	
	public void setSteps(int steps) {
		this.steps = steps;
	}
	
	public String toString(Color color) {
		if (steps == 0) return "NA";
		return color.name() + String.valueOf(pieceId) + "_" + String.valueOf(steps);
	}
	
	public Move copy() {
		Move m = new Move(this.pieceId,this.steps);
		return m;
	}
}
