package cop701.common;

public enum Color {
	R("Red"),
	G("Green"),
	Y("Yellow"),
	B("Blue");
	
	private String fullName;
	
	private Color(String fullName) {
		this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}	
	
}
