package cop701.bot;

import static org.junit.Assert.*;

import java.security.SecureRandom;
import java.util.Random;

import org.junit.Test;

public class ProbabilityTest {

	private int oppwin[][] = new int[27][27];
	private int wewin[][] = new int[27][27];
	private int tot[][] = new int[27][27];
	private double we[][] = new double[27][27];
	private double opp[][] = new double[27][27];
	private Random rnd;
	
	@Test
	public void test() {
		rnd = new SecureRandom();
		for (int i=0; i<27; i++) // our distance
			for (int j=0; j<27; j++) { // opp distance
				oppwin[i][j] = 0;
				wewin[i][j] = 0;
				tot[i][j] = 0;
				int cnt = 98989;
				while (cnt > 0) {
					int res = calc(i,j,0); // (self,opp,turn: 0=opp, 1=self)
					if (res == -1) oppwin[i][j]++;
					if (res == 1) wewin[i][j]++;
					tot[i][j]++;
					cnt--;
				}
				we[i][j] = Math.round((wewin[i][j]*1.0/tot[i][j]) * 10000.0) / 10000.0;
				opp[i][j] = Math.round((oppwin[i][j]*1.0/tot[i][j]) * 10000.0) / 10000.0;
				//System.out.println("("+i+","+j+"): "+ weRoundOff + "  " + oppRoundOff);
			}
		System.out.print("private final double weCapture[][] = { ");
		for (int i=0; i<27; i++) {
			if (i > 0) System.out.print(",");
			System.out.print("{");
			for (int j=0; j<27; j++) {
				if (j > 0) System.out.print(",");
				System.out.print(we[i][j]);
			}
			System.out.print("}");
		}
		System.out.println(" };");
		
		System.out.print("private final double oppCapture[][] = { ");
		for (int i=0; i<27; i++) {
			if (i > 0) System.out.print(",");
			System.out.print("{");
			for (int j=0; j<27; j++) {
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
			if (turn == 0) return calc(i,j-6-dice,1-turn);
			else return calc(i-6-dice,j,1-turn);
		}
		dice = rnd.nextInt(6)+1;
		if (dice < 6) {
			if (turn == 0) return calc(i,j-12-dice,1-turn);
			else return calc(i-12-dice,j,1-turn);
		}
		return calc(i,j,1-turn); // duck
	}
	
	

}
