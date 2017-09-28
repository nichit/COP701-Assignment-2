package cop701.bot;

import static org.junit.Assert.*;

import java.security.SecureRandom;
import java.util.Random;

import org.junit.Test;

public class ProbabilityTest {

	private int oppwin[][] = new int[53][53];
	private int wewin[][] = new int[53][53];
	private double we[][] = new double[53][53];
	private double opp[][] = new double[53][53];
	private Random rnd;
	
	@Test
	public void test() {
		rnd = new SecureRandom();
		for (int i=0; i<53; i++) // our distance
			for (int j=0; j<53; j++) { // opp distance
				oppwin[i][j] = 0;
				wewin[i][j] = 0;
				int cnt = 0;
				while (cnt < 98909) {
					int res = calc(i,j,0); // (self,opp,turn: 0=opp, 1=self)
					if (res == -1) oppwin[i][j]++;
					if (res == 1) wewin[i][j]++;
					cnt++;
				}
				we[i][j] = Math.round((wewin[i][j]*1.0/cnt) * 10000.0) / 10000.0;
				opp[i][j] = Math.round((oppwin[i][j]*1.0/cnt) * 10000.0) / 10000.0;
				System.out.println("("+i+","+j+"): "+ we[i][j]+ "  " + opp[i][j]);
			}
		System.out.print("private final double weCapture[][] = { ");
		for (int i=0; i<53; i++) {
			if (i > 0) System.out.print(",");
			System.out.print("{");
			for (int j=0; j<53; j++) {
				if (j > 0) System.out.print(",");
				System.out.print(we[i][j]);
			}
			System.out.print("}");
		}
		System.out.println(" };");
		
		System.out.print("private final double oppCapture[][] = { ");
		for (int i=0; i<53; i++) {
			if (i > 0) System.out.print(",");
			System.out.print("{");
			for (int j=0; j<53; j++) {
				if (j > 0) System.out.print(",");
				System.out.print(opp[i][j]);
			}
			System.out.print("}");
		}
		System.out.println(" };");
		assertTrue(true);
	}

	private int calc(int i, int j, int turn) {
		if (i <= 0 || j <= 0) return 0;
		if (i == j && i%13 != 4 && i%13 != 12) {
			if (turn == 0) return 1; // we win
			else return -1; // opp win
		}
		if (i == j && turn == 1) return 1;
		int dice = rnd.nextInt(6)+1;
		if (dice < 6) {
			if (turn == 0) return calc(i,j-dice,1-turn);
			else return calc(i-dice,j,1-turn);
		}
		dice = rnd.nextInt(6)+1;
		if (dice < 6) {
			if (turn == 0) {
				if (i%13 != 4 && i%13 != 12) {
					if (j-6 == i) return calc(i,j-6,1-turn);
					else if (j-dice == i) return calc(i,j-dice,1-turn);
				}
				return calc(i,j-6-dice,1-turn);
			}
			else {
				if (j%13 != 4 && j%13 != 12) {
					if (i-6 == j) return calc(i-6,j,1-turn);
					else if (i-dice == j) return calc(i-dice,j,1-turn);
				}
				return calc(i-6-dice,j,1-turn);
			}
		}
		dice = rnd.nextInt(6)+1;
		if (dice < 6) {
			if (turn == 0) {
				if (i%13 != 4 && i%13 != 12) {
					if (j-6 == i) return calc(i,j-6,1-turn);
					else if (j-12 == i) return calc(i,j-12,1-turn);
					else if (j-dice == i) return calc(i,j-dice,1-turn);
					else if (j-6-dice == i) return calc(i,j-6-dice,1-turn);
				}
				return calc(i,j-12-dice,1-turn);
			}
			else {
				if (j%13 != 4 && j%13 != 12) {
					if (i-6 == j) return calc(i-6,j,1-turn);
					else if (i-12 == j) return calc(i-12,j,1-turn);
					else if (i-dice == j) return calc(i-dice,j,1-turn);
					else if (i-6-dice == j) return calc(i-6-dice,j,1-turn);
				}
				return calc(i-12-dice,j,1-turn);
			}
		}
		return calc(i,j,1-turn); // duck
	}
	
	

}
