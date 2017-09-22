package cop701.common;

public class Move {

	private int id;
	private int steps; // steps == 0 is a NA move
	
	public Move(int id, int steps) {
		this.id = id;
		this.steps = steps;
	}
	
	public Move(String move) {
		this.id = Character.getNumericValue(move.charAt(1));
		this.steps = Character.getNumericValue(move.charAt(3));
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getSteps() {
		return steps;
	}
	
	public void setSteps(int steps) {
		this.steps = steps;
	}
	
	public String toString(Color color) {
		if (steps == 0) return "NA";
		return color.name() + String.valueOf(id) + "_" + String.valueOf(steps);
	}
}
