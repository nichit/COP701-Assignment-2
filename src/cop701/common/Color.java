package cop701.common;

public enum Color {
	R("Red"),
	B("Blue"),
	Y("Yellow"),
	G("Green");
	
	private String fullName;
	
	private Color(String fullName) {
		this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}	
	
}
