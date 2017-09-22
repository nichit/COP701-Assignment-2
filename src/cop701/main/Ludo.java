package cop701.main;

import java.util.Scanner;

public class Ludo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		manualMode();
	}
	
	public static void manualMode() {
		new LudoSimpleInteractiveUI();
		Scanner s = new Scanner(System.in);
		while (s.hasNextLine()) {
			String str = s.nextLine();
			System.err.println("[bot] " + str);
		}
		s.close();
	}
	
	public static void submitText(String input) {
		System.err.println("[input] " + input);
		System.out.println(input);
		System.out.flush();
	}
	

}
