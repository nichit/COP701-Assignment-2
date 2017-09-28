package cop701.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import cop701.bot.AI;
import cop701.bot.HunterAI;
import cop701.bot.RandomAI;
import cop701.common.Color;
import cop701.common.GameState;
import cop701.common.Move;
import cop701.gui.LudoGUI;
import cop701.gui.LudoSimpleInteractiveUI;

public class Ludo {

	private static boolean manualMode = false;
	/**
	 * We assume we are always player 0, because 0 is a lucky number :)
	 * @param args
	 */
	public static void main(String[] args) {
		AI ai = new HunterAI(); // Default AI
		for (int i=0; i<args.length; i++) {
			if (args[i].equals("--manual")) manualMode = true;
			if (args[i].equals("--ai")) {
				if (i+1 < args.length && args[i+1].equals("random"))
					ai = new RandomAI();
				else if (i+1 < args.length && args[i+1].equals("hunter"))
					ai = new HunterAI();
				i++;
			}
		}
		
		Scanner s = new Scanner(System.in);
		String input;
		String[] tokens;
		
		// Init game state
		input = s.nextLine(); tokens = input.split(" ");
		System.err.println("[input] " + input); System.err.flush();
		int pid = Integer.valueOf(tokens[0]);
		pid--;
		int timeLimit = Integer.valueOf(tokens[1]); // in seconds
		int gameMode = Integer.valueOf(tokens[2]);
		int showGUI = Integer.valueOf(tokens[3]);
		Map<Integer, Color> colorMap = new HashMap<Integer, Color>();
		for (Color c : Color.values()) {
			if (c.ordinal() == gameMode + pid * 2)
				colorMap.put(0, c);
			if (c.ordinal() == gameMode + (1-pid) * 2)
				colorMap.put(1, c);
		}
		Color playerColor = colorMap.get(0);
		
		GameState gameState = new GameState(colorMap);
		if (showGUI == 1) {
			LudoGUI gui = new LudoGUI();
			gui.changeState(gameState);
		}
		if (manualMode) {
			new LudoSimpleInteractiveUI(gameState);
		}
		
		/**
		 * Get dice:
		 * e.g. PLAYER 0 ROLLED 3 SIXES, AND THUS A DUCK
		 * 		PLAYER 0 ROLLED 4
		 * 		PLAYER 0 ROLLED 6 2
		 * 		PLAYER 0 ROLLED 6 6 5
		 * Get move:
		 * e.g. R0_1
		 * 		R0_1<next>R3_4
		 * 		NA
		 * Get your dice:
		 * e.g. YOU ROLLED 3 SIXES, AND THUS A DUCK
		 * 		YOU ROLLED 5
		 * 		YOU ROLLED 6 2
		 */
		
		if (pid == 1) { // 2nd player initialization
			// Get dice
			input = s.nextLine(); tokens = input.split(" ");
			System.err.println("[input] " + input); System.err.flush();
			// Get move
			input = s.nextLine(); tokens = input.split("<next>");
			System.err.println("[input] " + input); System.err.flush();
			
			for (int i=0; i<tokens.length; i++) {
				if (!tokens[i].equals("NA")) {
					gameState.updatePiece(1, new Move(tokens[i]));
				}	
			}
			printPieces(gameState);
		}
		
		while (timeLimit > 0) {
			System.err.println("[bot] <THROW>"); System.err.flush();
			System.out.println("<THROW>"); System.out.flush();
			
			// Get your dice
			input = s.nextLine(); tokens = input.split(" ");
			System.err.println("[input] " + input); System.err.flush();

			if (!manualMode) {
				boolean duck = false;
				for (int i=2; i<tokens.length; i++)
					if (tokens[i].equals("DUCK")) {
						duck = true;
					}
				
				String moveStr;
				if (!duck) {
					List<Integer> diceSet = new ArrayList<Integer>();
					for (int i=2; i<tokens.length; i++)
						diceSet.add(Integer.valueOf(tokens[i]));
	
					List<Move> moveList = ai.getMoveList(gameState, diceSet);
				
					List<String> moveStrList = new ArrayList<String>();
					for (Move move : moveList) {
						gameState.updatePiece(0, move);
						moveStrList.add(move.toString(playerColor));
					}
					moveStr = String.join("<next>", moveStrList);
				}
				else {
					moveStr = "NA";
				}
				
				System.err.println("[bot] " + moveStr); System.err.flush();
				System.out.println(moveStr); System.out.flush();
				printPieces(gameState);
			}
			
			// Get opponent dice / REPEAT
			input = s.nextLine(); tokens = input.split(" ");
			System.err.println("[input] " + input); System.err.flush();
			if (!tokens[0].equals("REPEAT")) {
				boolean oppoRepeat = false;
				do {
					if (oppoRepeat) {
						// Get opponent dice
						input = s.nextLine(); tokens = input.split(" ");
						System.err.println("[input] " + input); System.err.flush();
					}
					oppoRepeat = false;
					// Get move
					input = s.nextLine(); tokens = input.split("<next>");
					System.err.println("[input] " + input); System.err.flush();
					for (int i=0; i<tokens.length; i++) {
						if (!tokens[i].equals("NA") && !tokens[i].equals("REPEAT")) {
							gameState.updatePiece(1, new Move(tokens[i]));
						}
						if (tokens[i].equals("REPEAT")) {
							oppoRepeat = true;
						}
					}
					printPieces(gameState);
				} while (oppoRepeat);
			}
			
		}
		
		s.close();
	}
	
	public static void printPieces(GameState gameState) {
		for (int i=0; i<2; i++) {
			System.err.print("Player " + i + ": [ ");
			for (int j=0; j<4; j++) {
				System.err.print(gameState.getPieces()[i][j] + " ");
			}
			System.err.println("]"); System.err.flush();
		}
	}
		
	public static void manualMode() {
		new LudoSimpleInteractiveUI(null);
		Scanner s = new Scanner(System.in);
		while (s.hasNextLine()) {
			String str = s.nextLine();
			System.err.println("[input] " + str);
		}
		s.close();
	}
	
	public static void submitText(GameState gameState, String input) {
		String[] tokens = input.split("<next>");
		
		for (int i=0; i<tokens.length; i++) {
			if (!tokens[i].equals("NA")) {
				gameState.updatePiece(0, new Move(tokens[i]));
			}	
		}
		printPieces(gameState);
		
		System.err.println("[bot] " + input);
		System.out.println(input);
		System.out.flush();
	}
	
}
