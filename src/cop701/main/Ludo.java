package cop701.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import cop701.bot.AI;
import cop701.bot.RandomAI;
import cop701.common.Color;
import cop701.common.GameState;
import cop701.common.Move;

public class Ludo {

	
	/**
	 * We assume we are always player 0, because 0 is a lucky number :)
	 * @param args
	 */
	public static void main(String[] args) {
		//manualMode();
		
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
		Map<Integer, Color> colorMap = new HashMap<Integer, Color>();
		for (Color c : Color.values()) {
			if (c.ordinal() == gameMode + pid * 2)
				colorMap.put(0, c);
			if (c.ordinal() == gameMode + (1-pid) * 2)
				colorMap.put(1, c);
		}
		Color playerColor = colorMap.get(0);
		
		GameState gameState = new GameState(colorMap);
		AI ai = new RandomAI();
		
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
			
			// Get opponent dice / REPEAT
			input = s.nextLine(); tokens = input.split(" ");
			System.err.println("[input] " + input); System.err.flush();
			if (!tokens[0].equals("REPEAT")) {
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
