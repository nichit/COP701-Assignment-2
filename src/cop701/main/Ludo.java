package cop701.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cop701.bot.AI;
import cop701.bot.RandomAI;
import cop701.common.Color;
import cop701.common.GameState;
import cop701.common.Move;

public class Ludo {

	public static void main(String[] args) {
		//manualMode();
		
		Scanner s = new Scanner(System.in);
		String input;
		String[] tokens;
		
		// Init game state
		input = s.nextLine(); tokens = input.split(" ");
		int pid = Integer.valueOf(tokens[0]);
		pid--;
		int timeLimit = Integer.valueOf(tokens[1]); // in seconds
		int gameMode = Integer.valueOf(tokens[2]);
		Color playerColor = null;
		Color opponentColor = null;
		for (Color c : Color.values()) {
			if (c.ordinal() == gameMode * 2 + pid)
				playerColor = c;
			if (c.ordinal() == gameMode * 2 + 1-pid)
				opponentColor = c;
		}
		
		GameState gameState = new GameState();
		AI ai = new RandomAI();
		
		/**
		 * Get dice:
		 * e.g. PLAYER 0 ROLLED 0
		 * 		PLAYER 0 ROLLED 4
		 * 		PLAYER 0 ROLLED 6 2
		 * 		PLAYER 0 ROLLED 6 6 5
		 * Get move:
		 * e.g. R0_1
		 * 		R0_1<next>R3_4
		 * 		NA
		 * Get your dice:
		 * e.g. YOU ROLLED 0
		 * 		YOU ROLLED 5
		 * 		YOU ROLLED 6 2
		 */
		
		if (pid == 1) { // 2nd player initialization
			// Get dice
			input = s.nextLine(); tokens = input.split(" ");
			// Get move
			input = s.nextLine(); tokens = input.split("<next>");
			for (int i=0; i<tokens.length; i++) {
				if (!tokens[i].equals("NA")) {
					gameState.updatePiece(1-pid, new Move(tokens[i]));
				}	
			}
		}
		
		while (true) {			
			System.out.println("<THROW>"); System.out.flush();
			
			// Get your dice
			input = s.nextLine(); tokens = input.split(" ");
			List<Integer> diceSet = new ArrayList<Integer>();
			for (int i=2; i<tokens.length; i++)
				diceSet.add(Integer.valueOf(tokens[i]));

			List<Move> moveList = ai.getMoveList(gameState, diceSet);
			
			List<String> moveStrList = new ArrayList<String>();
			for (Move move : moveList) {
				gameState.updatePiece(pid, move);
				moveStrList.add(move.toString(playerColor));
			}
			String moveStr = String.join("<next>", moveStrList);
			
			System.out.println(moveStr); System.out.flush();
			
			// Get dice / REPEAT
			input = s.nextLine(); tokens = input.split(" ");
			if (!tokens[0].equals("REPEAT")) {
				// Get move
				input = s.nextLine(); tokens = input.split("<next>");
				for (int i=0; i<tokens.length; i++) {
					if (!tokens[i].equals("NA")) {
						gameState.updatePiece(1-pid, new Move(tokens[i]));
					}	
				}
			}
			
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
